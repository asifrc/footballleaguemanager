package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TradeServiceTest {

    private TradeService tradeService;
    private PlayerService playerService;

    @Before
    public void setUp() throws Exception {
        playerService = new PlayerService();
        tradeService = new TradeService(playerService);

    }

    @Test
    public void shouldUpdatePlayerTeamsWhenPassedNewTeams() {
        playerService.setPlayers(createSomePlayers());

        List<String> names = new ArrayList<String>(Arrays.asList("A", "B", "C"));
        List<String> currentTeams = new ArrayList<String>(Arrays.asList("Team A", "Team B", "Team C"));
        List<String> numbers = new ArrayList<String>(Arrays.asList("Default Number", "Default Number", "Default Number"));
        List<String> ages = new ArrayList<String>(Arrays.asList("20", "20", "20"));
        List<String> newTeams = new ArrayList<String>(Arrays.asList("Team D", "-- Trade --", "-- Trade --"));

        tradeService.tradePlayers(names, currentTeams, numbers, ages, newTeams);

        Set<Player> playersA = playerService.getPlayersFrom("Team A");
        Set<Player> playersD = playerService.getPlayersFrom("Team D");

        assertThat(playersA.size(), is(0));
        assertThat(playersD.size(), is(1));

    }

    private HashSet<Player> createSomePlayers() {
        HashSet<Player> players = new HashSet<Player>();
        players.add(new PlayerBuilder().withName("A").withTeam("Team A").build());
        players.add(new PlayerBuilder().withName("B").withTeam("Team B").build());
        players.add(new PlayerBuilder().withName("C").withTeam("Team C").build());
        return players;
    }
}