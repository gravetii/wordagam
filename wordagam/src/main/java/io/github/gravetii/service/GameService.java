package io.github.gravetii.service;

import io.github.gravetii.game.Game;
import io.github.gravetii.game.GameFactory;

import java.util.logging.Logger;

public class GameService {

    private static final Logger logger = Logger.getLogger(GameService.class.getCanonicalName());

    private GameFactory factory;

    public GameService() {
        this.factory = GameFactory.get();
    }

    public Game get() {
        return this.factory.fetch();
    }

    public void close() {
        if (this.factory != null) {
            this.factory.close();
            logger.info("Successfully closed instance of GameFactory.");
        }
    }

}
