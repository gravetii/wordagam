package io.github.gravetii.game;

public class GameService {

  private GameFactory factory;

  public GameService() {
    this.factory = GameFactory.get();
  }

  public static void init() {
    GameFactory.get();
  }

  public static void close() {
    GameFactory.close();
  }

  public Game fetch() {
    return this.factory.fetch();
  }
}
