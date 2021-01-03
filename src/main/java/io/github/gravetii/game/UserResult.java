package io.github.gravetii.game;

import io.github.gravetii.model.WordResult;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserResult {
  private final Map<String, WordResult> wordToResultMap = new LinkedHashMap<>();
  private int totalScore = 0;

  public void add(String word, WordResult result) {
    this.wordToResultMap.put(word, result);
    this.totalScore += result.getScore();
  }

  public WordResult get(String word) {
    return this.wordToResultMap.get(word);
  }

  public Map<String, WordResult> all() {
    return this.wordToResultMap;
  }

  public boolean exists(String word) {
    return this.wordToResultMap.containsKey(word);
  }

  public int getTotalScore() {
    return this.totalScore;
  }

  public int getWordCount() {
    return this.wordToResultMap.size();
  }
}
