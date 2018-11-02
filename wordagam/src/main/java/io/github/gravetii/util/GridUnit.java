package io.github.gravetii.util;

import java.net.URL;

public class GridUnit {

    private Alphabet alphabet;

    private String image;

    public GridUnit(Alphabet alphabet) {
        this.alphabet = alphabet;
        URL url = ClassLoader.getSystemResource("images/" + alphabet.get() + ".png");
        this.image = url.getPath();
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

    public String getImage() {
        return image;
    }

}
