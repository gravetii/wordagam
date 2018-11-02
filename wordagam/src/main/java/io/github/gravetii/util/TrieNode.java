package io.github.gravetii.util;

public class TrieNode {

    private TrieNode[] children;
    private boolean word;

    TrieNode() {
        this.children = new TrieNode[Constants.ALPHABET_COUNT];
        this.word = false;
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public void setChildren(TrieNode[] children) {
        this.children = children;
    }

    public boolean isWord() {
        return word;
    }

    public void setWord() {
        this.word = true;
    }

}
