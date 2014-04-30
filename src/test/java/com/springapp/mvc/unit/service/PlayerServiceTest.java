package com.springapp.mvc.unit.service;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.PlayerService;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerServiceTest {
    private PlayerService playerService;

    @Test
    public void shouldReturnListOfPlayers() throws Exception {
        ArrayList<Player> playerList = new ArrayList<Player>();
        playerList.add(new Player("Bob", "0"));
        playerList.add(new Player("Sally", "1"));

        playerService = new PlayerService(playerList);

        assertEquals(playerList, playerService.getPlayers());
    }

}
