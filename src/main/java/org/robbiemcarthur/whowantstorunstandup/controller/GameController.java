package org.robbiemcarthur.whowantstorunstandup.controller;

import org.robbiemcarthur.whowantstorunstandup.model.game.rockpaperscissors.RockPaperScissorsMoveRequest;
import org.robbiemcarthur.whowantstorunstandup.service.websocket.WebSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {
    private final WebSocketService webSocketService;

    public GameController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @MessageMapping("/play")
    public void processMove(@Payload RockPaperScissorsMoveRequest rockPaperScissorsMoveRequest) {
        webSocketService.handleRockPaperScissorsMove(rockPaperScissorsMoveRequest);
    }

    @MessageMapping("/join")
    public void addUser(SimpMessageHeaderAccessor headerAccessor, String username) {
        webSocketService.handleJoin(username);
    }

    @MessageMapping("/spin")
    public void spinWheel() {
        webSocketService.handleSpinTheWheel();
    }

    @MessageMapping("/leave")
    public void leaveGame(String username) {
        webSocketService.handleLeave(username);
    }

    @MessageMapping("/clear-players")
    public void clearPlayers() {
        webSocketService.handleClearPlayers();
    }
}
