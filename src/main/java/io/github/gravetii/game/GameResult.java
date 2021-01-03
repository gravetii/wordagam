package io.github.gravetii.game;

import io.github.gravetii.model.GridPoint;
import io.github.gravetii.model.WordResult;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
  private final Map<String, WordResult> wordToResultMap = new LinkedHashMap<>();
  private int totalScore = 0;
  private GameQuality quality;

  public void put(String word, int score, List<GridPoint> seq) {
    if (!this.wordToResultMap.containsKey(word)) {
      this.wordToResultMap.put(word, new WordResult(word, score, seq));
      this.totalScore += score;
    }
  }

  public boolean exists(String word) {
    return this.wordToResultMap.containsKey(word);
  }

  public int getTotalScore() {
    return this.totalScore;
  }

  public int getPoints(String word) {
    return this.wordToResultMap.get(word).getScore();
  }

  public Map<String, WordResult> all() {
    return this.wordToResultMap;
  }

  public WordResult get(String word) {
    return this.wordToResultMap.get(word);
  }

  public void defineQuality() {
    this.quality =
        Arrays.stream(GameQuality.values())
            .dropWhile(x -> this.wordToResultMap.size() <= x.getMinCount())
            .findFirst()
            .orElse(GameQuality.LOW);
  }

  public GameQuality getQuality() {
    return this.quality;
  }

  @Override
  public String toString() {
    return "GameResult{"
        + "wordToResultMap="
        + wordToResultMap
        + ", totalScore="
        + totalScore
        + ", quality="
        + quality
        + '}';
  }
}
