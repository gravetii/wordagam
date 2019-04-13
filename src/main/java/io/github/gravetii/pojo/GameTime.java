package io.github.gravetii.pojo;

public class GameTime {
  private int minutes;
  private int seconds;

  public GameTime(int minutes, int seconds) {
    this.minutes = minutes;
    this.seconds = seconds;
  }

  public static GameTime from(String value) {
    String[] tokens = value.split("\\$");
    int minutes = Integer.parseInt(tokens[0]);
    int seconds = Integer.parseInt(tokens[1]);
    return new GameTime(minutes, seconds);
  }

  public int getMinutes() {
    return minutes;
  }

  public int getSeconds() {
    return seconds;
  }

  public String to() {
    return this.minutes + "$" + this.seconds;
  }

  public int getValueInSeconds() {
    return this.minutes * 60 + this.seconds;
  }
}
