package org.robbiemcarthur.whowantstorunstandup.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class GameController {

    public static class Move {
        public String player;
        public String choice;
    }

    public static class GameResult {
        public String player1;
        public String move1;
        public String player2;
        public String move2;
        public String winner;

        public GameResult(String p1, String m1, String p2, String m2) {
            this.player1 = p1;
            this.move1 = m1;
            this.player2 = p2;
            this.move2 = m2;
            this.winner = determineWinner(m1, m2);
        }

        private String determineWinner(String move1, String move2) {
            if (move1.equals(move2)) return "Draw!";
            if ((move1.equals("rock") && move2.equals("scissors")) ||
                    (move1.equals("scissors") && move2.equals("paper")) ||
                    (move1.equals("paper") && move2.equals("rock"))) {
                return player1 + " Wins!";
            }
            return player2 + " Wins!";
        }
    }

    private static Move player1Move = null;
    private static Move player2Move = null;

    @MessageMapping("/play")
    @SendTo("/topic/game-results")
    public GameResult processMove(Move move) {
        if (player1Move == null) {
            player1Move = move;
            return null; // Wait for second player
        } else {
            player2Move = move;
            GameResult result = new GameResult(
                    player1Move.player, player1Move.choice,
                    player2Move.player, player2Move.choice
            );
            player1Move = player2Move = null; // Reset for next round
            return result;
        }
    }

    private static final Map<String, String> activeUsers = new ConcurrentHashMap<>();
    private final Random random = new Random();

    @MessageMapping("/join")
    @SendTo("/topic/players")
    public Set<String> addUser(SimpMessageHeaderAccessor headerAccessor, String username) {
        activeUsers.putIfAbsent(username, username);
        return activeUsers.keySet();
    }

    @MessageMapping("/spin")
    @SendTo("/topic/wheel-results")
    public String spinWheel() {
        List<String> players = new ArrayList<>(activeUsers.values());
        if (players.isEmpty()) return "No players available!";
        return players.get(random.nextInt(players.size()));
    }

    @MessageMapping("/leave")
    @SendTo("/topic/players")
    public List<String> leaveGame(String username) {
        activeUsers.remove(username);
        return new ArrayList<>(activeUsers.keySet());
    }

    @MessageMapping("/clear-players")
    @SendTo("/topic/players")
    public List<String> clearPlayers() {
        activeUsers.clear();
        return new ArrayList<>(activeUsers.keySet());
    }
}
