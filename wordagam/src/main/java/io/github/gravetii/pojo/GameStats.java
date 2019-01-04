package io.github.gravetii.pojo;

import io.github.gravetii.game.GameResult;
import io.github.gravetii.game.UserResult;

public class GameStats {
  private int totalWordsCount;
  private int totalScore;
  private int userWordsCount;
  private int userScore;
  private float wordsPercent;
  private float scorePercent;

  public GameStats(GameResult gameResult, UserResult userResult) {
    this.totalWordsCount = gameResult.all().size();
    this.totalScore = gameResult.getTotalScore();
    this.userScore = userResult.getTotalScore();
    this.userWordsCount = userResult.getWordCount();
    this.wordsPercent = ((float) this.userWordsCount * 100) / this.totalWordsCount;
    this.scorePercent = ((float) this.userScore * 100) / this.totalScore;
  }

  public String to() {
    String result =
            "\n\nWords - %d/%d\n"
                    + "Words percentage - %.2f\n\n"
                    + "Score - %d/%d\n"
                    + "Score percentage - %.2f\n\n";

    return String.format(
            result,
            userWordsCount,
            totalWordsCount,
            wordsPercent,
            userScore,
            totalScore,
            scorePercent);
  }
}
