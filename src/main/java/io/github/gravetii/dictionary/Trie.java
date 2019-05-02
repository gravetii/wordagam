package io.github.gravetii.dictionary;

import io.github.gravetii.util.Alphabet;

public class Trie {
  private Trie[] children = new Trie[26];
  private int score = 0;

  /**
   * Add a new word from the English dictionary to this trie.
   *
   * @param word the word to add.
   */
  public void insert(String word) {
    char[] arr = word.toCharArray();
    int score = 0;

    Trie node = this;
    for (char c : arr) {
      int idx = c - 'a';
      Alphabet alphabet = Alphabet.values()[idx];
      if (node.children[idx] == null) {
        node.children[idx] = new Trie();
      }

      node = node.children[idx];
      score += alphabet.getScore();
    }

    node.score = score;
  }

  /**
   * Find if the given word belongs in the trie by returning its score.
   *
   * @param word the input word.
   * @return the score of the word if it's valid, 0 otherwise.
   */
  public int search(String word) {
    Trie node = this;
    char[] arr = word.toCharArray();
    for (char c : arr) {
      int idx = c - 'a';
      if (node.children[idx] == null) {
        return 0;
      }

      node = node.children[idx];
    }

    return node != null ? node.score : 0;
  }

  /**
   * Find if the given word is a prefix of some other word in the trie.
   *
   * @param word the input word.
   * @return true if the word is a prefix of some other word, false otherwise.
   */
  public boolean prefix(String word) {
    Trie node = this;
    char[] arr = word.toCharArray();
    for (char c : arr) {
      int idx = c - 'a';
      if (node.children[idx] == null) {
        return false;
      }

      node = node.children[idx];
    }

    return node != null;
  }
}
