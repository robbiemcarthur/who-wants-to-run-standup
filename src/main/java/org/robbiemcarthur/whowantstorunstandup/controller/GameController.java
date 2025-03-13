package org.robbiemcarthur.whowantstorunstandup.controller;

import org.robbiemcarthur.whowantstorunstandup.model.GameResult;
import org.robbiemcarthur.whowantstorunstandup.model.Move;
import org.robbiemcarthur.whowantstorunstandup.service.GameService;
import org.robbiemcarthur.whowantstorunstandup.service.PlayerService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@Controller
public class GameController {
    private final GameService gameService;
    private final PlayerService playerService;

    public GameController(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
    }

    @MessageMapping("/play")
    @SendTo("/topic/game-results")
    public GameResult processMove(Move move) {
        return gameService.processMove(move);
    }

    @MessageMapping("/join")
    @SendTo("/topic/players")
    public Set<String> addUser(SimpMessageHeaderAccessor headerAccessor, String username) {
        return playerService.addUser(username);
    }

    @MessageMapping("/spin")
    @SendTo("/topic/wheel-results")
    public String spinWheel() {
        return playerService.spinWheel();
    }

    @MessageMapping("/leave")
    @SendTo("/topic/players")
    public List<String> leaveGame(String username) {
        return playerService.leaveGame(username);
    }

    @MessageMapping("/clear-players")
    @SendTo("/topic/players")
    public List<String> clearPlayers() {
        return playerService.clearGame();
    }
}