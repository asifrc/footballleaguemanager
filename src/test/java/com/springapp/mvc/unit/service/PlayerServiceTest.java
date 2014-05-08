package com.springapp.mvc.unit.service;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.PlayerService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PlayerServiceTest {
    private PlayerService playerService;
    private ArrayList<Player> playerList;
    private Player playerCubs;
    private Player playerYankees;

    @Before
    public void setUp() throws Exception {
        playerList = new ArrayList<Player>();

        playerCubs = new PlayerBuilder()
                .withName("Bob")
                .withNumber("0")
                .withTeam("Cubs")
                .build();
        playerList.add(playerCubs);

        playerYankees = new PlayerBuilder()
                .withName("Sally")
                .withNumber("1")
                .build();
        playerList.add(playerYankees);

        playerService = new PlayerService();
        playerService.setPlayerList(playerList);
    }

    @Test
    public void shouldReturnListOfPlayers() throws Exception {
        assertEquals(playerList, playerService.getPlayerList());
    }

    @Test
    public void shouldFindAPlayerByNameAndNumberWhenThereIsAMatch() throws Exception {
        Player addedPlayer = new PlayerBuilder().withName("Ayanga").withNumber("70").build();
        playerList.add(addedPlayer);
        playerList.add(new PlayerBuilder().withName("Random").withNumber("50").build());

        Player foundPlayer = playerService.findPlayerByName("Ayanga");

        assertEquals(addedPlayer, foundPlayer);
    }

    @Test
    public void shouldFilterPlayersByMinimumAge() {
        Player underAge = new Player("Daniel", "Team1", "1", 17);
        Player oldEnough = new Player("Jane", "Team2", "2", 18);
        playerList.add(underAge);
        playerList.add(oldEnough);
        playerService.setPlayerList(playerList);

        List<Player> filteredPlayerList = playerService.getPlayersWithMinimumAge(18);
        playerList.remove(underAge);

        assertFalse(playerList.contains(underAge));
        assertEquals(playerList, filteredPlayerList);
    }

    @Test
    public void shouldReturnPlayersFromASpecifiedTeam() {
        List<Player> expectedPlayers = new ArrayList<Player>();
        expectedPlayers.add(playerCubs);
        List<Player> actualPlayers = playerService.getPlayersFrom("Cubs");

        assertEquals(expectedPlayers, actualPlayers);
    }
}