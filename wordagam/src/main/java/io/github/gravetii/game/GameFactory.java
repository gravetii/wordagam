package io.github.gravetii.game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GameFactory {

    private static final Logger logger = Logger.getLogger(GameFactory.class.getCanonicalName());

    private static final int MAX_GAMES_IN_QUEUE = 5;

    private Dictionary dictionary;

    private LinkedBlockingDeque<Game> queue;

    private ExecutorService executor;

    public GameFactory() {
        this.dictionary = new Dictionary();
        this.queue = new LinkedBlockingDeque<>(MAX_GAMES_IN_QUEUE);
        this.executor = Executors.newFixedThreadPool(1);
        this.bootstrap();
    }

    private Game create() {
        Game game = new Game(this.dictionary);
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
        logger.info("Fetched new game: " + game);
        return game;
    }

    private void backFill() {
        int n = MAX_GAMES_IN_QUEUE - queue.size();
        if (n > 0) {
            this.executor.submit(new GameLoaderTask(n));
        }
    }

    public void close() {
        try {
            this.executor.shutdown();
            boolean terminated = this.executor.awaitTermination(2, TimeUnit.SECONDS);
            if (!terminated) {
                this.executor.shutdownNow();
            }

            this.queue.clear();
        }
        catch (Exception e) {
            logger.info("Error while closing GameFactory: " + e);
        }
    }

    private class GameLoaderTask implements Runnable {

        private int n;

        GameLoaderTask(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            for (int i=1;i<n;++i) {
                Game game = create();
                if (game.getQuality() == Quality.HIGH) {
                    queue.offerFirst(game);
                }
                else {
                    queue.offerLast(game);
                }
            }
        }
    }

}
