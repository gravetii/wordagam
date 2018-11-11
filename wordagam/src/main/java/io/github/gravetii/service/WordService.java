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

    public static void main(String[] args) throws Exception {
        WordService service = new WordService();
        System.out.println(service.search("mat"));
        Thread.sleep(1000);
        System.out.println(service.search("mat"));
        System.out.println(service.search("got"));
        System.out.println(service.search("hip"));
        System.out.println(service.search("asdaegff"));
    }
}
