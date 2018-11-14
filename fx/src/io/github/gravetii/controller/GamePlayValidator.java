package io.github.gravetii.controller;

import io.github.gravetii.game.Game;
import io.github.gravetii.util.GridUnit;

import java.util.LinkedList;

public class GamePlayValidator {

    private Game game;
    private LinkedList<GridUnit> seq;
    private StringBuilder builder;

    public GamePlayValidator(Game game) {
        this.game = game;
        this.seq = new LinkedList<>();
        this.builder = new StringBuilder();
    }

    private void checkWord() {
        if (game.getAllWords().contains(this.builder.toString())) {
            System.out.println(this.builder.toString());
        }
    }

    private void invalidate() {
        this.seq.clear();
        this.builder = new StringBuilder();
    }

    private void append(GridUnit unit) {
        this.seq.add(unit);
        this.builder.append(unit.getLetter());
    }

    private boolean validateSubsequentClick(GridUnit unit) {
        if (this.seq.contains(unit) || !unit.isNeighbor(this.seq.getLast())) {
            this.checkWord();
            this.invalidate();
            return false;
        }
        else {
            this.append(unit);
            System.out.println(this.builder.toString());
            return true;
        }
    }

    public boolean validateClick(GridUnit unit) {
        if (this.seq.isEmpty()) {
            this.append(unit);
            return true;
        }
        else {
            return this.validateSubsequentClick(unit);
        }
    }

}
