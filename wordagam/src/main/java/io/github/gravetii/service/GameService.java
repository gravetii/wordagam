package io.github.gravetii.service;

import io.github.gravetii.game.Game;
import io.github.gravetii.game.Quality;
import io.github.gravetii.scheduler.TaskScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GameService {

    private static final Logger logger = Logger.getLogger(GameService.class.getCanonicalName());

    private static final int MAX_GAMES_IN_QUEUE = 5;

    private LinkedBlockingDeque<Game> queue;

    private ExecutorService executor;

    public GameService() {
        this.queue = new LinkedBlockingDeque<>(MAX_GAMES_IN_QUEUE);
        this.executor = Executors.newFixedThreadPool(1);
        this.bootstrap();
    }

    private Game create() {
        Game game = new Game();
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
        return game;
    }

    private void backFill() {
        int n = MAX_GAMES_IN_QUEUE - queue.size();
        if (n > 0) {
            TaskScheduler.get().submit(new GameLoaderTask(n));
        }
    }

    public void close() {
        try {
            this.executor.shutdown();
            this.executor.awaitTermination(5, TimeUnit.SECONDS);
            this.executor.shutdownNow();
        }
        catch (Exception e) {
            logger.info("Error while shutting down GameFactory executor: " + e);
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
