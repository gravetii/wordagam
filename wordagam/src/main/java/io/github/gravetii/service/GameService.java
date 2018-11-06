package io.github.gravetii.service;

import io.github.gravetii.game.Game;
import io.github.gravetii.game.GameFactory;

public class GameService {

    private GameFactory factory;

    public static void init() {
        new GameService();
    }

    public GameService() {
        this.factory = GameFactory.get();
    }

    public Game get() {
        return this.factory.fetch();
    }
}
