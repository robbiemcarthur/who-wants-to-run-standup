package org.robbiemcarthur.whowantstorunstandup.service;

import org.robbiemcarthur.whowantstorunstandup.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Set<String> addUser(String username) {
        playerRepository.addPlayer(username);
        return playerRepository.findAllPlayers();
    }

    public List<String> leaveGame(String username) {
        playerRepository.removePlayer(username);
        return playerRepository.getRandomizedPlayers();
    }

    public List<String> clearGame() {
        playerRepository.clearPlayers();
        return playerRepository.getRandomizedPlayers();
    }

    public String spinWheel() {
        List<String> players = playerRepository.getRandomizedPlayers();
        if (players.isEmpty()) {
            return "No players available!";
        }
        return players.get((int) (Math.random() * players.size()));
    }
}