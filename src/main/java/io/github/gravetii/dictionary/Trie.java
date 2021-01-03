package io.github.gravetii.dictionary;

import io.github.gravetii.util.Alphabet;

public class Trie {
  private final TrieNode root;

  Trie() {
    this.root = new TrieNode();
  }

  /**
   * Add a new word from the English dictionary to this trie.
   *
   * @param word the word to add.
   */
  public void insert(String word) {
    char[] arr = word.toCharArray();
    TrieNode node = root;
    int score = 0;

    for (char c : arr) {
      int idx = c - 'a';
      Alphabet alphabet = Alphabet.values()[idx];
      TrieNode[] children = node.getChildren();
      if (children[idx] == null) children[idx] = new TrieNode();
      node = children[idx];
      score += alphabet.getScore();
    }

    node.setScore(score);
  }

  /**
   * Find if the given word belongs in the trie by returning its score.
   *
   * @param word the input word.
   * @return the score of the word if it's valid, 0 otherwise.
   */
  public int search(String word) {
    TrieNode node = root;
    char[] arr = word.toCharArray();

    for (char c : arr) {
      int idx = c - 'a';
      TrieNode[] children = node.getChildren();
      if (children[idx] == null) return 0;
      node = children[idx];
    }

    return node != null ? node.getScore() : 0;
  }

  /**
   * Find if the given word is a prefix of some other word in the trie.
   *
   * @param word the input word.
   * @return true if the word is a prefix of some other word, false otherwise.
   */
  public boolean prefix(String word) {
    TrieNode node = root;
    char[] arr = word.toCharArray();

    for (char c : arr) {
      int idx = c - 'a';
      TrieNode[] children = node.getChildren();
      if (children[idx] == null) return false;
      node = children[idx];
    }

    return node != null;
  }
}
