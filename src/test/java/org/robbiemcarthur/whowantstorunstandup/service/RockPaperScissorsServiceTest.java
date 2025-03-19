package org.robbiemcarthur.whowantstorunstandup.service;

import org.junit.jupiter.api.Test;
import org.robbiemcarthur.whowantstorunstandup.model.game.rockpaperscissors.RockPaperScissorsMoveRequest;
import org.robbiemcarthur.whowantstorunstandup.model.game.rockpaperscissors.RockPaperScissorsResult;

import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsServiceTest {

    private final RockPaperScissorsService service = new RockPaperScissorsService();

    @Test
    void should_ReturnNull_When_FirstMoveIsRecorded() {
        RockPaperScissorsMoveRequest move = new RockPaperScissorsMoveRequest("Alice", "rock");
        RockPaperScissorsResult result = service.move(move);

        assertNull(result);
    }

    @Test
    void should_ProcessSecondMove_AndReturnResult() {
        RockPaperScissorsMoveRequest move1 = new RockPaperScissorsMoveRequest("Alice", "rock");
        RockPaperScissorsMoveRequest move2 = new RockPaperScissorsMoveRequest("Bob", "scissors");

        service.move(move1);
        RockPaperScissorsResult result = service.move(move2);

        assertNotNull(result);
        assertEquals("Alice", result.winner());
    }

    @Test
    void should_HandleDraw_WhenBothMovesAreTheSame() {
        RockPaperScissorsMoveRequest move1 = new RockPaperScissorsMoveRequest("Alice", "rock");
        RockPaperScissorsMoveRequest move2 = new RockPaperScissorsMoveRequest("Bob", "rock");

        service.move(move1);
        RockPaperScissorsResult result = service.move(move2);

        assertNotNull(result);
        assertEquals("Draw!", result.winner());
    }
}