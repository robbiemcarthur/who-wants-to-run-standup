package org.robbiemcarthur.whowantstorunstandup.repository;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PlayerRepository {
    private final Map<String, String> activeUsers = new ConcurrentHashMap<>();

    public Set<String> findAllPlayers() {
        return activeUsers.keySet();
    }

    public void addPlayer(String username) {
        activeUsers.putIfAbsent(username, username);
    }

    public void removePlayer(String username) {
        activeUsers.remove(username);
    }

    public void clearPlayers() {
        activeUsers.clear();
    }

    public List<String> getRandomizedPlayers() {
        return new ArrayList<>(activeUsers.values());
    }
}