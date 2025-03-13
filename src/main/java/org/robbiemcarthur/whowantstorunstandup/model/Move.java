package org.robbiemcarthur.whowantstorunstandup.model;

public class Move {
    private String player;
    private String choice;

    public Move() {}

    public Move(String player, String choice) {
        this.player = player;
        this.choice = choice;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
}