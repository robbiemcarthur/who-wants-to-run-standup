package org.robbiemcarthur.whowantstorunstandup.service;

import org.junit.jupiter.api.Test;
import org.robbiemcarthur.whowantstorunstandup.repository.PlayerRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpinTheWheelServiceTest {

    private final PlayerRepository repository = mock(PlayerRepository.class);
    private final SpinTheWheelService service = new SpinTheWheelService(repository);

    @Test
    void should_AddUser_AndReturnAllPlayers() {
        when(repository.findAllPlayers()).thenReturn(Set.of("Alice", "Bob"));

        Set<String> players = service.addUser("Alice");

        verify(repository, times(1)).addPlayer("Alice");
        assertEquals(2, players.size());
    }

    @Test
    void should_RemoveUser_AndReturnRemainingPlayers() {
        when(repository.getRandomizedPlayers()).thenReturn(List.of("Bob"));

        List<String> players = service.leaveGame("Alice");

        verify(repository, times(1)).removePlayer("Alice");
        assertEquals(1, players.size());
        assertEquals("Bob", players.get(0));
    }

    @Test
    void should_ClearAllPlayersAndReturnEmptyList() {
        when(repository.getRandomizedPlayers()).thenReturn(List.of());

        List<String> players = service.clearGame();

        verify(repository, times(1)).clearPlayers();
        assertTrue(players.isEmpty());
    }

    @Test
    void should_SpinWheel_AndReturnRandomPlayer() {
        when(repository.getRandomizedPlayers()).thenReturn(List.of("Alice", "Bob"));
        String player = service.spinWheel();

        verify(repository, times(1)).getRandomizedPlayers();
        assertNotNull(player);
    }

    @Test
    void should_SpinWheel_AndReturnNoPlayersMessage_IfEmpty() {
        when(repository.getRandomizedPlayers()).thenReturn(List.of());
        String result = service.spinWheel();

        assertEquals("No players available!", result);
    }
}