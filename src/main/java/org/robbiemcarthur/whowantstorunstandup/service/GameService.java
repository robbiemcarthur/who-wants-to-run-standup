package org.robbiemcarthur.whowantstorunstandup.service;

import org.robbiemcarthur.whowantstorunstandup.model.GameResult;
import org.robbiemcarthur.whowantstorunstandup.model.Move;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private Move player1Move = null;

    public GameResult processMove(Move move) {
        if (player1Move == null) {
            player1Move = move;
            return null; // Waiting for another player
        } else {
            GameResult result = new GameResult(
                    player1Move.getPlayer(), player1Move.getChoice(),
                    move.getPlayer(), move.getChoice()
            );
            player1Move = null; // Reset
            return result;
        }
    }
}