package io.github.gravetii.pojo;

public class GameStats {
  private int totalWordsCount;
  private int totalScore;
  private int userWordsCount;
  private int userScore;

  private GameStats(int totalWordsCount, int totalScore, int userWordsCount, int userScore) {
    this.totalWordsCount = totalWordsCount;
    this.totalScore = totalScore;
    this.userWordsCount = userWordsCount;
    this.userScore = userScore;
  }

  public int getTotalWordsCount() {
    return totalWordsCount;
  }

  public int getTotalScore() {
    return totalScore;
  }

  public int getUserWordsCount() {
    return userWordsCount;
  }

  public int getUserScore() {
    return userScore;
  }

  public String to() {
    String result =
        "\n\nWords - %d/%d\n"
            + "Words percentage - %.2f\n\n"
            + "Score - %d/%d\n"
            + "Score percentage - %.2f\n\n";

    float wordsCountPercent = ((float) this.userWordsCount * 100) / this.totalWordsCount;
    float scorePercent = ((float) this.userScore * 100) / this.totalScore;

    return String.format(
        result,
        userWordsCount,
        totalWordsCount,
        wordsCountPercent,
        userScore,
        totalScore,
        scorePercent);
  }

  public static class Builder {
    private int totalWordsCount;
    private int totalScore;
    private int userWordsCount;
    private int userScore;

    public Builder() {}

    public Builder setTotalWordsCount(int totalWordsCount) {
      this.totalWordsCount = totalWordsCount;
      return this;
    }

    public Builder setTotalScore(int totalScore) {
      this.totalScore = totalScore;
      return this;
    }

    public Builder setUserWordsCount(int userWordsCount) {
      this.userWordsCount = userWordsCount;
      return this;
    }

    public Builder setUserScore(int userScore) {
      this.userScore = userScore;
      return this;
    }

    public GameStats build() {
      return new GameStats(totalWordsCount, totalScore, userWordsCount, userScore);
    }
  }
}
