package io.github.gravetii.dictionary;

import io.github.gravetii.util.Alphabet;

public class Trie {
  private final TrieNode root;

  Trie() {
    this.root = new TrieNode();
  }

  /**
   * Add a new word to this trie.
   *
   * @param word the word to add
   */
  public void insert(String word) {
    TrieNode node = root;
    int score = 0;
    for (char ch : word.toCharArray()) {
      int idx = ch - 'a';
      Alphabet alphabet = Alphabet.values()[idx];
      TrieNode[] children = node.getChildren();
      if (children[idx] == null) children[idx] = new TrieNode();
      node = children[idx];
      score += alphabet.getScore();
    }

    node.setScore(score);
  }

  /**
   * Search for the given word in the trie and return its score.
   *
   * @param word the input word
   * @return the score of the word: > 0 if the word exists, = 0 if the word exists as a prefix, < 0
   *     if the word doesn't exist
   */
  public int search(String word) {
    TrieNode node = root;
    for (char ch : word.toCharArray()) {
      int idx = ch - 'a';
      TrieNode[] children = node.getChildren();
      if (children[idx] == null) return -1;
      node = children[idx];
    }

    return node != null ? node.getScore() : -1;
  }
}
