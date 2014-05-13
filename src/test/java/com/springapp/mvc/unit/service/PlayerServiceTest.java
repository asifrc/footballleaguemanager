package com.springapp.mvc.unit.service;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.PlayerService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PlayerServiceTest {
    private PlayerService playerService;
    private HashSet<Player> players;
    private Player playerCubs;
    private Player playerYankees;

    @Before
    public void setUp() {
        players = new HashSet<Player>();

        playerCubs = new PlayerBuilder()
                .withName("Bob")
                .withNumber("0")
                .withTeam("Cubs")
                .build();
        players.add(playerCubs);

        playerYankees = new PlayerBuilder()
                .withName("Sally")
                .withNumber("1")
                .build();
        players.add(playerYankees);

        playerService = new PlayerService();
        playerService.setPlayers(players);
    }

    @Test
    public void shouldReturnPlayers() {
        assertEquals(players, playerService.getPlayers());
    }

    @Test
    public void shouldFindAPlayerByNameAndNumberWhenThereIsAMatch() {
        Player addedPlayer = new PlayerBuilder().withName("Ayanga").withNumber("70").build();
        players.add(addedPlayer);
        players.add(new PlayerBuilder().withName("Random").withNumber("50").build());

        Player foundPlayer = playerService.findPlayerByName("Ayanga");

        assertEquals(addedPlayer, foundPlayer);
    }

    @Test
    public void shouldFilterPlayersByMinimumAge() {
        Player underAge = new Player("Daniel", "Team1", "1", 17);
        Player oldEnough = new Player("Jane", "Team2", "2", 18);
        players.add(underAge);
        players.add(oldEnough);
        playerService.setPlayers(players);

        Set<Player> filteredPlayers = playerService.getPlayersWithMinimumAge(18);
        players.remove(underAge);

        assertFalse(players.contains(underAge));
        assertEquals(players, filteredPlayers);
    }

    @Test
    public void shouldReturnPlayersFromASpecifiedTeam() {
        Set<Player> expectedPlayers = new HashSet<Player>();
        expectedPlayers.add(playerCubs);
        Set<Player> actualPlayers = playerService.getPlayersFrom("Cubs");

        assertEquals(expectedPlayers, actualPlayers);
    }
}