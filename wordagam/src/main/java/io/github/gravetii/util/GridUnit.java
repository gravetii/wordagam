package io.github.gravetii.util;

public class GridUnit {

    private Alphabet alphabet;

    public GridUnit(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public String getLetter() {
        return alphabet.get();
    }

    public int getPoints() {
        return alphabet.getPoints();
    }

    public int getWeight() {
        return alphabet.getWeight();
    }

}
