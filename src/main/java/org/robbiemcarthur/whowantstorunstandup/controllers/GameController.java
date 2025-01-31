package org.robbiemcarthur.whowantstorunstandup.controllers;

import org.robbiemcarthur.whowantstorunstandup.models.Game;
import org.robbiemcarthur.whowantstorunstandup.services.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/rock-paper-scissors")
    public Game playRockPaperScissors(@RequestParam String playerMove) {
        return gameService.playRockPaperScissors(playerMove);
    }

    @PostMapping("/spin-the-wheel")
    public Game spinTheWheel() {
        return gameService.spinTheWheel();
    }
}
