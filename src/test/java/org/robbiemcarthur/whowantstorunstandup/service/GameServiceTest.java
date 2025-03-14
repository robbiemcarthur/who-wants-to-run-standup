package org.robbiemcarthur.whowantstorunstandup.service;

import org.junit.jupiter.api.Test;
import org.robbiemcarthur.whowantstorunstandup.model.GameResult;
import org.robbiemcarthur.whowantstorunstandup.model.Move;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private final GameService service = new GameService();

    @Test
    void should_ReturnNull_When_FirstMoveIsRecorded() {
        Move move = new Move("Alice", "rock");
        GameResult result = service.processMove(move);

        assertNull(result);
    }

    @Test
    void should_ProcessSecondMove_AndReturnResult() {
        Move move1 = new Move("Alice", "rock");
        Move move2 = new Move("Bob", "scissors");

        service.processMove(move1);
        GameResult result = service.processMove(move2);

        assertNotNull(result);
        assertEquals("Alice", result.getWinner());
    }

    @Test
    void should_HandleDraw_WhenBothMovesAreTheSame() {
        Move move1 = new Move("Alice", "rock");
        Move move2 = new Move("Bob", "rock");

        service.processMove(move1);
        GameResult result = service.processMove(move2);

        assertNotNull(result);
        assertEquals("Draw!", result.getWinner());
    }
}