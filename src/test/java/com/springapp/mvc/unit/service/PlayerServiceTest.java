package com.springapp.mvc.unit.service;

import com.springapp.mvc.PlayerBuilder;
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

        playerList.add(new PlayerBuilder()
                .withName("Bob")
                .withNumber("0")
                .build());

        playerList.add(new PlayerBuilder()
                .withName("Sally")
                .withNumber("1")
                .build());

        playerService = new PlayerService(playerList);
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

}