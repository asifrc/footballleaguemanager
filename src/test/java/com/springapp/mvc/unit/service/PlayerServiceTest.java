package com.springapp.mvc.unit.service;

import com.springapp.mvc.service.PlayerService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerServiceTest {
    private PlayerService playerService;

    @Test
    public void shouldReturnListOfPlayers() throws Exception {
        playerService = new PlayerService();
        int numberOfPlayers = playerService.getPlayers().size();
        assertEquals(2, numberOfPlayers);
    }

}
