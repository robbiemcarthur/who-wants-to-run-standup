package org.robbiemcarthur.whowantstorunstandup.service.websocket;

import lombok.RequiredArgsConstructor;
import org.robbiemcarthur.whowantstorunstandup.model.game.rockpaperscissors.RockPaperScissorsResult;
import org.robbiemcarthur.whowantstorunstandup.model.game.rockpaperscissors.RockPaperScissorsMoveRequest;
import org.robbiemcarthur.whowantstorunstandup.service.RockPaperScissorsService;
import org.robbiemcarthur.whowantstorunstandup.service.SpinTheWheelService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;
    private final RockPaperScissorsService gameService;
    private final SpinTheWheelService spinTheWheelService;

    public void handleRockPaperScissorsMove(RockPaperScissorsMoveRequest rockPaperScissorsMoveRequest) {
        RockPaperScissorsResult result = gameService.move(rockPaperScissorsMoveRequest);
        if (result != null) {
            messagingTemplate.convertAndSend("/topic/game-results", result);
        }
    }

    public void handleJoin(String username) {
        Set<String> players = spinTheWheelService.addUser(username);
        messagingTemplate.convertAndSend("/topic/players", players);
    }

    public void handleLeave(String username) {
        List<String> updatedPlayers = spinTheWheelService.leaveGame(username);
        messagingTemplate.convertAndSend("/topic/players", updatedPlayers);
    }

    public void handleClearPlayers() {
        List<String> updatedPlayers = spinTheWheelService.clearGame();
        messagingTemplate.convertAndSend("/topic/players", updatedPlayers);
    }

    public void handleSpinTheWheel() {
        String winner = spinTheWheelService.spinWheel();
        messagingTemplate.convertAndSend("/topic/game-results", winner);
    }
}
