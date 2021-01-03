package io.github.gravetii.model;

import io.github.gravetii.game.GameResult;
import io.github.gravetii.game.UserResult;

public class GameStats {
  private final GameResult gameResult;
  private final UserResult userResult;

  public GameStats(GameResult gameResult, UserResult userResult) {
    this.gameResult = gameResult;
    this.userResult = userResult;
  }

  @Override
  public String toString() {
    String result =
        "Words - %d/%d\n"
            + "Words percentage - %.2f\n\n"
            + "Score - %d/%d\n"
            + "Score percentage - %.2f";

    int userWordsCount = this.userResult.getWordCount();
    int totalWordsCount = this.gameResult.all().size();
    int userScore = this.userResult.getTotalScore();
    int totalScore = this.gameResult.getTotalScore();

    float wordsPercent = ((float) userWordsCount * 100) / totalWordsCount;
    float scorePercent = ((float) userScore * 100) / totalScore;

    return String.format(
        result,
        this.userResult.getWordCount(),
        gameResult.all().size(),
        wordsPercent,
        this.userResult.getTotalScore(),
        this.gameResult.getTotalScore(),
        scorePercent);
  }
}
