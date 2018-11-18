package io.github.gravetii.controller;

import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;

import java.util.LinkedList;

public class GamePlayStyler {

    private LinkedList<ImageView> seq;

    public GamePlayStyler() {
        this.seq = new LinkedList<>();
    }

    public void apply(ImageView imgView) {
        imgView.setScaleX(0.92);
        imgView.setScaleY(0.92);
        imgView.setBlendMode(BlendMode.SRC_ATOP);
        this.seq.add(imgView);
    }

    public void reset() {
        while (!this.seq.isEmpty()) {
            ImageView imgView = this.seq.pop();
            imgView.setScaleX(1.0);
            imgView.setScaleY(1.0);
            imgView.setBlendMode(BlendMode.MULTIPLY);
        }
    }
}
