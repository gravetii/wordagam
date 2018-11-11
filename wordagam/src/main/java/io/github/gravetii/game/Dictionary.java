package io.github.gravetii.game;

import io.github.gravetii.util.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Dictionary {

  private static final Logger logger = Logger.getLogger(Dictionary.class.getCanonicalName());

  private static final String WORDS_FILE = "words.txt";

  private Trie trie;

  Dictionary() {
    BufferedReader reader = null;

    try {
      this.trie = new Trie();
      InputStream istream = ClassLoader.getSystemResourceAsStream(WORDS_FILE);
      reader = new BufferedReader(new InputStreamReader(istream));
      String word;
      while ((word = reader.readLine()) != null) {
        word = word.trim();
        trie.insert(word);
      }

      logger.info("Completed loading all words into the trie.");
    } catch (IOException e) {
      logger.info("Error: Could not load all words into the trie.");
      throw new RuntimeException(e);
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        logger.info("Error: Could not successfully close reader.");
      }
    }
  }

  public boolean search(String word) {
    return this.trie.search(word);
  }

  public boolean prefix(String word) {
    return this.trie.prefix(word);
  }
}