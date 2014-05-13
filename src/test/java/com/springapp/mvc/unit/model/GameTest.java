package com.springapp.mvc.unit.model;

import com.springapp.mvc.model.Game;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {
    @Test
    public void shouldGetFirstTeam() throws Exception {
        Game game = new Game("Cowboys", 9, "Indians", 10);
        assertEquals("Cowboys", game.getTeam(0));
        assertEquals("Indians", game.getTeam(1));
    }

    @Test
    public void shouldGetResultsForAWinLossGame() throws Exception {
        Game game = new Game("Cowboys", 9, "Indians", 10);
        assertEquals("L", game.resultFor("Cowboys"));
        assertEquals("W", game.resultFor("Indians"));
    }

    @Test
    public void shouldGetResultsForATie() throws Exception {
        Game game = new Game("Cowboys", 10, "Indians", 10);
        assertEquals("T", game.resultFor("Cowboys"));
        assertEquals("T", game.resultFor("Indians"));
    }
}
