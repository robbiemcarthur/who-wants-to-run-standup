package org.robbiemcarthur.whowantstorunstandup.model.game.rockpaperscissors;

public record RockPaperScissorsResult(
        String player1,
        String move1,
        String player2,
        String move2,
        String winner
) {
    public RockPaperScissorsResult(String player1, String move1, String player2, String move2) {
        this(player1, move1, player2, move2, determineWinner(player1, move1, player2, move2));
    }

    private static String determineWinner(String player1, String move1, String player2, String move2) {
        if (move1.equals(move2)) return "Draw!";
        if ((move1.equals("rock") && move2.equals("scissors")) ||
                (move1.equals("scissors") && move2.equals("paper")) ||
                (move1.equals("paper") && move2.equals("rock"))) {
            return player1 + " Wins!";
        }
        return player2 + " Wins!";
    }
}