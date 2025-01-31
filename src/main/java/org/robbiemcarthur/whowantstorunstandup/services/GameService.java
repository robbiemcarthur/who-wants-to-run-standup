package org.robbiemcarthur.whowantstorunstandup.services;

import org.robbiemcarthur.whowantstorunstandup.models.Game;
import org.robbiemcarthur.whowantstorunstandup.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {
    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public Game playRockPaperScissors(String playerMove) {
        String[] moves = {"rock", "paper", "scissors"};
        String computerMove = moves[new Random().nextInt(moves.length)];
        String result;

        if (playerMove.equals(computerMove)) {
            result = "draw";
        } else if (
                (playerMove.equals("rock") && computerMove.equals("scissors")) ||
                        (playerMove.equals("paper") && computerMove.equals("rock")) ||
                        (playerMove.equals("scissors") && computerMove.equals("paper"))
        ) {
            result = "win";
        } else {
            result = "lose";
        }

        Game game = new Game();
        game.setName("Rock Paper Scissors");
        game.setResult(result);
        return repository.save(game);
    }

    public Game spinTheWheel() {
        String[] outcomes = {"win", "lose", "draw"};
        String result = outcomes[new Random().nextInt(outcomes.length)];

        Game game = new Game();
        game.setName("Spin the Wheel");
        game.setResult(result);
        return repository.save(game);
    }
}

