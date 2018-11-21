package io.github.gravetii.dictionary;

import io.github.gravetii.util.AppLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Dictionary {

  private static final String WORDS_FILE = "words.txt";

  private Trie trie;

  public Dictionary() {
    BufferedReader reader = null;
    try {
      this.trie = new Trie();
      InputStream istream = ClassLoader.getSystemResourceAsStream(WORDS_FILE);
      reader = new BufferedReader(new InputStreamReader(istream));
      String word;
      while ((word = reader.readLine()) != null) {
        word = word.trim().toLowerCase();
        trie.insert(word);
      }

      AppLogger.info(getClass().getCanonicalName(), "Completed loading all words into the trie.");
    } catch (IOException e) {
      AppLogger.info(getClass().getCanonicalName(), "Could not load all words into the trie.");
      throw new RuntimeException(e);
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        AppLogger.severe(getClass().getCanonicalName(), "Could not successfully close reader.");
      }
    }
  }

  public static void main(String[] args) {
    Dictionary dictionary = new Dictionary();
    System.out.println(dictionary.search("mask"));
    System.out.println(dictionary.search("silhouette"));
    System.out.println(dictionary.prefix("silhou"));
  }

  public boolean search(String word) {
    return this.trie.search(word);
  }

  public boolean prefix(String word) {
    return this.trie.prefix(word);
  }
}
