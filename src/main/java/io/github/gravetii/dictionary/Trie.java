package io.github.gravetii.dictionary;

public class Trie {
  private TrieNode root;

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

    for (char c : arr) {
      int idx = c - 'a';
      TrieNode[] children = node.getChildren();
      if (children[idx] == null) {
        children[idx] = new TrieNode();
      }

      node = children[idx];
    }

    node.setWord();
  }

  private boolean search(String word, boolean prefix) {
    char[] arr = word.toCharArray();

    TrieNode node = root;

    for (char c : arr) {
      int idx = c - 'a';
      TrieNode[] children = node.getChildren();
      if (children[idx] == null) {
        return false;
      }

      node = children[idx];
    }

    if (node == null) {
      return false;
    }

    return prefix || node.isWord();
  }

  /**
   * Find if the given word belongs in the trie or not.
   *
   * @param word the input word.
   * @return true if the word is present in the trie, false otherwise.
   */
  public boolean search(String word) {
    return search(word, false);
  }

  /**
   * Find if the given word is a prefix of some other word in the trie.
   *
   * @param word the input word.
   * @return true if the word is a prefix of some other word, false otherwise.
   */
  public boolean prefix(String word) {
    return search(word, true);
  }
}
