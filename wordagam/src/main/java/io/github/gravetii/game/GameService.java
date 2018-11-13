package io.github.gravetii.game;

public class GameService {

    private GameFactory factory;

    public GameService() {
        this.factory = GameFactory.get();
    }

    public Game fetch() {
        return this.factory.fetch();
    }

    public static void close() {
        GameFactory.close();
    }
}
