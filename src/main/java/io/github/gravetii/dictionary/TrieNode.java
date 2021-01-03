package io.github.gravetii.dictionary;

public class TrieNode {
  private static final int ALPHABET_COUNT = 26;

  private final TrieNode[] children;
  private int score;

  TrieNode() {
    this.children = new TrieNode[ALPHABET_COUNT];
    this.score = 0;
  }

  public TrieNode[] getChildren() {
    return children;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}
