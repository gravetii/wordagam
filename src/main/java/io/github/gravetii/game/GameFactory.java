package io.github.gravetii.game;

import io.github.gravetii.dictionary.Dictionary;
import io.github.gravetii.util.AppLogger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class GameFactory {
  private static final int MAX_GAMES_IN_QUEUE = 5;
  private static volatile GameFactory instance;

  private final Dictionary dictionary;
  private final LinkedBlockingDeque<Game> queue;
  private final ExecutorService executor;

  private GameFactory() {
    this.dictionary = new Dictionary();
    this.queue = new LinkedBlockingDeque<>(MAX_GAMES_IN_QUEUE);
    this.executor = Executors.newFixedThreadPool(2);
    this.backFill();
  }

  public static GameFactory get() {
    if (instance == null) {
      synchronized (GameFactory.class) {
        if (instance == null) {
          instance = new GameFactory();
          AppLogger.fine(GameFactory.class.getCanonicalName(), "Created instance of GameFactory");
        }
      }
    }

    return instance;
  }

  public static void close() {
    if (instance != null) {
      instance.shutdown();
      instance = null;
    }
  }

  private Game create() {
    Game game = null;
    GameQuality q = GameQuality.LOW;
    while (q == GameQuality.LOW) {
      game = new Game(this.dictionary);
      q = game.getQuality();
    }

    return game;
  }

  public synchronized Game fetch() {
    Game game = this.queue.poll();
    if (game == null) game = this.create();
    this.backFill();
    AppLogger.fine(getClass().getCanonicalName(), "Fetched new game: " + game);
    return game;
  }

  private void backFill() {
    this.backFill(MAX_GAMES_IN_QUEUE - queue.size());
  }

  private void backFill(int n) {
    while (n-- > 0) {
      this.executor.submit(
          () -> {
            Game game = create();
            this.pushToQueue(game);
          });
    }
  }

  private void pushToQueue(Game game) {
    GameQuality q = game.getQuality();
    if (q == GameQuality.HIGH) {
      queue.offerFirst(game);
    } else if (q == GameQuality.MEDIUM) {
      queue.offerLast(game);
    }
  }

  public void shutdown() {
    try {
      this.executor.shutdown();
      boolean terminated = this.executor.awaitTermination(5, TimeUnit.SECONDS);
      if (!terminated) this.executor.shutdownNow();
      this.queue.clear();
    } catch (Exception e) {
      AppLogger.severe(getClass().getCanonicalName(), "Error while closing GameFactory: " + e);
    }
  }
}
