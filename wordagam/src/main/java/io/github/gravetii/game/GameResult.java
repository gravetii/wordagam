package io.github.gravetii.game;

import io.github.gravetii.pojo.WordResult;

import java.util.Map;

public class GameResult {

  private static final int MIN_WORDS_COUNT = 400;

  private final Map<String, WordResult> wordToResultMap;
  private final int totalScore;
  private final int wordCount;

  public GameResult(Map<String, WordResult> wordToResultMap, int totalScore) {

    this.wordToResultMap = wordToResultMap;
    this.totalScore = totalScore;
    this.wordCount = this.wordToResultMap.size();
  }

  public Quality defineQuality() {
    int sz = this.wordToResultMap.size();
    return sz >= MIN_WORDS_COUNT ? Quality.HIGH : Quality.LOW;
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

  public WordResult getWordResult(String word) {
    return this.wordToResultMap.get(word);
  }

  private int getWordCount() {
    return this.wordCount;
  }

  @Override
  public String toString() {
    return '{' +
            "wordCount=" + wordCount +
            ", totalScore=" + totalScore +
            ", all=" + wordToResultMap +
            '}';
  }
}
