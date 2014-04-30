package com.springapp.mvc.unit.service;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.PlayerService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerServiceTest {
    private PlayerService playerService;
    private ArrayList<Player> playerList;

    @Before
    public void setUp() throws Exception {
        playerList = new ArrayList<Player>();
        playerList.add(new Player("Bob", "0"));
        playerList.add(new Player("Sally", "1"));

        playerService = new PlayerService(playerList);
    }

    @Test
    public void shouldReturnListOfPlayers() throws Exception {
        assertEquals(playerList, playerService.getPlayerList());
    }

    @Test
    public void shouldFindAPlayerByNameAndNumberWhenThereIsAMatch() throws Exception {
        Player addedPlayer = new Player("Ayanga", "70");
        playerList.add(addedPlayer);
        playerList.add(new Player("Random", "50"));

        Player foundPlayer = playerService.findPlayer("Ayanga", "70");

        assertEquals(addedPlayer, foundPlayer);
    }


}