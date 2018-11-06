package io.github.gravetii.service;

import io.github.gravetii.util.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class WordLoader {

    private static final Logger logger = Logger.getLogger(WordLoader.class.getCanonicalName());

    private static final String WORDS_FILE = "words.txt";

    private static volatile WordLoader INSTANCE;

    private Trie trie;

    public static WordLoader get() {
        try {
            if (INSTANCE == null) {
                synchronized (WordLoader.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new WordLoader();
                        logger.info("Created instance of WordLoader.");
                    }
                }
            }

            return INSTANCE;
        }
        catch (IOException e) {
            System.out.println("Error while loading words in WordLoader thread");
            throw new RuntimeException(e);
        }
    }

    private WordLoader() throws IOException {
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
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public boolean search(String word) {
        return trie.search(word);
    }

    public boolean prefix(String word) {
        return trie.prefix(word);
    }

}
