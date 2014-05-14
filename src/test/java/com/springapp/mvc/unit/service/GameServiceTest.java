package com.springapp.mvc.unit.service;

import com.springapp.mvc.model.Game;
import com.springapp.mvc.service.GameService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameServiceTest {

    @Test
    public void shouldReturnWinLossTieRecordForATeamWithOneWin() throws Exception {
        Game game = new Game("Team0", 9, "Team1", 10);

        GameService gameService = new GameService();

        String actual = gameService.getWinLossTieRecordFor("Team0");
        String expected = "0-1-0";

        assertEquals(expected, actual);
    }
}