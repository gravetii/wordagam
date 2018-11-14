package io.github.gravetii.dictionary;

import io.github.gravetii.util.Constants;

public class TrieNode {

  private TrieNode[] children;
  private boolean word;

  TrieNode() {
    this.children = new TrieNode[Constants.ALPHABET_COUNT];
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

  void setWord() {
    this.word = true;
  }
}
