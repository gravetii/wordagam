package io.github.gravetii.dictionary;

public class TrieNode {
  private static final int ALPHABET_COUNT = 26;

  private TrieNode[] children;
  private boolean word;

  TrieNode() {
    this.children = new TrieNode[ALPHABET_COUNT];
    this.word = false;
  }

  public TrieNode[] getChildren() {
    return children;
  }

  public void setChildren(TrieNode[] children) {
    this.children = children;
  }

  public boolean isWord() {
    return word;
  }

  public void setWord() {
    this.word = true;
  }
}
