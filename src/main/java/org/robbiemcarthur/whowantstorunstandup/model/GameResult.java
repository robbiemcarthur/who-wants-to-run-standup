package org.robbiemcarthur.whowantstorunstandup.model;

import lombok.Getter;

public class GameResult {
    private final String player1;
    private final String player2;
    @Getter
    private final String winner;

    public GameResult(String player1, String move1, String player2, String move2) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = determineWinner(move1, move2);
    }

    private String determineWinner(String move1, String move2) {
        if (move1.equals(move2)) return "Draw!";
        if ((move1.equals("rock") && move2.equals("scissors")) ||
                (move1.equals("scissors") && move2.equals("paper")) ||
                (move1.equals("paper") && move2.equals("rock"))) {
            return player1 + " Wins!";
        }
        return player2 + " Wins!";
    }
}