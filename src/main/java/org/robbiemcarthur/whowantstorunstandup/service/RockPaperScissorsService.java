package org.robbiemcarthur.whowantstorunstandup.service;

import org.robbiemcarthur.whowantstorunstandup.model.game.rockpaperscissors.RockPaperScissorsResult;
import org.robbiemcarthur.whowantstorunstandup.model.game.rockpaperscissors.RockPaperScissorsMoveRequest;
import org.springframework.stereotype.Service;

@Service
public class RockPaperScissorsService {
    private RockPaperScissorsMoveRequest player1Move = null;

    public RockPaperScissorsResult move(final RockPaperScissorsMoveRequest move) {
        if (player1Move == null) {
            player1Move = move;
            return null; // Waiting for another player
        } else {
            RockPaperScissorsResult result = new RockPaperScissorsResult(
                    player1Move.player(), player1Move.choice(),
                    move.player(), move.choice()
            );
            player1Move = null; // Reset
            return result;
        }
    }
}