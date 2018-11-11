package io.github.gravetii;

import io.github.gravetii.service.GameService;
import io.github.gravetii.service.WordService;

import java.util.logging.Logger;

public class AppContext {

    private static final Logger logger = Logger.getLogger(AppContext.class.getCanonicalName());

    private static volatile AppContext INSTANCE;

    private WordService wordService;
    private GameService gameService;

    public static void create() {
        try {
            if (INSTANCE == null) {
                synchronized (AppContext.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new AppContext();
                    }
                }
            }
        }
        catch (Exception e) {
            logger.info("Error: Could not create AppContext");
            throw new RuntimeException(e);
        }
    }

    public static AppContext get() {
        return INSTANCE;
    }

    private AppContext() {
        this.wordService = new WordService();
        this.gameService = new GameService();
        logger.info("Created AppContext...");
    }

    public WordService words() {
        return wordService;
    }

    public GameService games() {
        return gameService;
    }

    public static void close() {
        if (INSTANCE != null) {
            INSTANCE.gameService.close();
        }

        logger.info("Successfully closed AppContext.");
    }

}
