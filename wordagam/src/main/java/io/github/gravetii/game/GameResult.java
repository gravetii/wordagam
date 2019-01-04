package io.github.gravetii.game;

import io.github.gravetii.pojo.WordResult;
import io.github.gravetii.util.GridPoint;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

  private Map<String, WordResult> wordToResultMap = new LinkedHashMap<>();
  private int totalScore = 0;
  private List<String> longestWords = new ArrayList<>();
  private int longestLength;

  public void put(String word, int score, List<GridPoint> seq) {
    if (!this.wordToResultMap.containsKey(word)) {
      this.wordToResultMap.put(word, new WordResult(word, score, seq));
      this.totalScore += score;
      this.checkLongest(word);
    }
  }

  private void checkLongest(String word) {
    int len = word.length();
    if (len < this.longestLength) {
      return;
    }

    if (len > this.longestLength) {
      this.longestWords.clear();
      this.longestLength = len;
    }

    this.longestWords.add(word.toUpperCase());
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

  public List<String> getLongestWords() {
    return this.longestWords;
  }

  @Override
  public String toString() {
    return '{'
        + "wordCount="
        + this.wordToResultMap.size()
        + ", totalScore="
        + totalScore
        + ", all="
        + wordToResultMap
        + '}';
  }
}
