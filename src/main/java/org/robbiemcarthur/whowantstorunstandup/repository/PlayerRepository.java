package org.robbiemcarthur.whowantstorunstandup.repository;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PlayerRepository {
    private final Map<String, String> activeUsers = new ConcurrentHashMap<>();

    // Initial population of the map
    {
        populateDefaultPlayers();
    }

    // Repopulate the map with default data
    private void populateDefaultPlayers() {
        activeUsers.put("Ajit", "Ajit");
        activeUsers.put("Craig D", "Craig D");
        activeUsers.put("Craig H", "Craig H");
        activeUsers.put("Declan", "Declan");
        activeUsers.put("Gayathri", "Gayathri");
        activeUsers.put("Joao", "Joao");
        activeUsers.put("Liam", "Liam");
        activeUsers.put("Mark", "Mark");
        activeUsers.put("Ritika", "Ritika");
        activeUsers.put("Tarannum", "Tarannum");
        activeUsers.put("Tripati", "Tripati");
        activeUsers.put("Viswanth", "Viswanth");
    }

    public Set<String> findAllPlayers() {
        if (activeUsers.isEmpty()) {
            populateDefaultPlayers(); // Repopulate the map if empty
        }
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