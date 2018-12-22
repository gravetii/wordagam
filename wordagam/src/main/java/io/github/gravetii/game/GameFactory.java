package io.github.gravetii.game;

import io.github.gravetii.dictionary.Dictionary;
import io.github.gravetii.util.AppLogger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class GameFactory {

  private static final int MAX_GAMES_IN_QUEUE = 5;
  private static volatile GameFactory INSTANCE;

  private Dictionary dictionary;
  private LinkedBlockingDeque<Game> queue;
  private ExecutorService executor;

  private GameFactory() {
    this.dictionary = new Dictionary();
    this.queue = new LinkedBlockingDeque<>(MAX_GAMES_IN_QUEUE);
    this.executor = Executors.newFixedThreadPool(1);
    this.bootstrap();
  }

  public static GameFactory get() {
    if (INSTANCE == null) {
      synchronized (GameFactory.class) {
        if (INSTANCE == null) {
          INSTANCE = new GameFactory();
          AppLogger.info(GameFactory.class.getCanonicalName(), "Created instance of GameFactory");
        }
      }
    }

    return INSTANCE;
  }

  public static void close() {
    if (INSTANCE != null) {
      INSTANCE.shutdown();
      INSTANCE = null;
    }
  }

  private Game create() {
    Game game = null;
    Quality q = Quality.LOW;

    while (q == Quality.LOW) {
      game = new Game(this.dictionary);
      q = game.getQuality();
    }

    return game;
  }

  private void bootstrap() {
    this.executor.submit(new GameLoaderTask(MAX_GAMES_IN_QUEUE));
  }

  public synchronized Game fetch() {
    Game game = this.queue.poll();
    if (game == null) {
      game = this.create();
    }

    this.backFill();
    AppLogger.info(getClass().getCanonicalName(), "Fetched new game: " + game);
    return game;
  }

  private void backFill() {
    int n = MAX_GAMES_IN_QUEUE - queue.size();
    if (n > 0) {
      this.executor.submit(new GameLoaderTask(n));
    }
  }

  public void shutdown() {
    try {
      this.executor.shutdown();
      boolean terminated = this.executor.awaitTermination(2, TimeUnit.SECONDS);
      if (!terminated) {
        this.executor.shutdownNow();
      }

      this.queue.clear();
    } catch (Exception e) {
      AppLogger.severe(getClass().getCanonicalName(), "Error while closing GameFactory: " + e);
    }
  }

  private class GameLoaderTask implements Runnable {

    private int n;

    GameLoaderTask(int n) {
      this.n = n;
    }

    @Override
    public void run() {
      for (int i = 1; i <= n; ++i) {
        Game game = create();
        queue.offerLast(game);
      }
    }
  }
}
