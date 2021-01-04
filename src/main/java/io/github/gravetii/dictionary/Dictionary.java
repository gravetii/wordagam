package io.github.gravetii.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Dictionary {

  private static final Logger logger = Logger.getLogger(Dictionary.class.getCanonicalName());

  private static final String WORDS_FILE = "words.txt";

  private final Trie trie;

  public Dictionary() {
    BufferedReader reader = null;
    try {
      this.trie = new Trie();
      InputStream inputStream = ClassLoader.getSystemResourceAsStream(WORDS_FILE);
      if (inputStream == null) throw new IOException("Invalid URL specified");
      reader = new BufferedReader(new InputStreamReader(inputStream));
      String word;
      while ((word = reader.readLine()) != null) {
        word = word.trim().toLowerCase();
        trie.insert(word);
      }

      logger.fine("Loaded all words into the trie");
    } catch (IOException e) {
      logger.severe("Error while loading words into the trie - " + e.getMessage());
      throw new RuntimeException(e);
    } finally {
      try {
        if (reader != null) reader.close();
      } catch (IOException e) {
        logger.severe("Error while closing reader - " + e.getMessage());
      }
    }
  }

  public static void main(String[] args) {
    Dictionary dictionary = new Dictionary();
    System.out.println(dictionary.search("mask"));
    System.out.println(dictionary.search("silhouette"));
    System.out.println(dictionary.prefix("silhou"));
  }

  public int search(String word) {
    return this.trie.search(word);
  }

  public boolean prefix(String word) {
    return this.trie.prefix(word);
  }
}
