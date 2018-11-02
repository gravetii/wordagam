package io.github.gravetii.service;

public class WordService {

    private WordLoader loader;

    public WordService() {
        this.loader = WordLoader.get();
    }

    public boolean search(String word) {
        return this.loader.search(word);
    }

    public boolean prefix(String word) {
        return this.loader.prefix(word);
    }

}
