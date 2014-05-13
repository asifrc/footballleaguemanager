package com.springapp.mvc.unit.service;

import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.CoachBuilder;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TeamServiceTest {
    @Mock
    private PlayerService stubbedPlayerService;
    @Mock
    private CoachService stubbedCoachService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldGetTeamsFromPlayers() throws Exception {
        Set<String> expectedTeams = createSomeTeams();
        when(stubbedPlayerService.getPlayers()).thenReturn(createSomePlayers());

        Set<String> actualTeams = new TeamService(stubbedPlayerService, stubbedCoachService).getTeams();

        assertSame(actualTeams.size(), expectedTeams.size());
        assertTrue(actualTeams.containsAll(expectedTeams));
    }

    @Test
    public void shouldNotContainDuplicateTeamsWhenThereAreMultiplePlayersFromTheSameTeam() throws Exception {
        HashSet<Player> players = createSomePlayers();
        players.add(new PlayerBuilder().withName("D").withTeam("Team B").build());

        Set<String> expectedTeams = createSomeTeams();
        when(stubbedPlayerService.getPlayers()).thenReturn(players);

        Set<String> actualTeams = new TeamService(stubbedPlayerService, stubbedCoachService).getTeams();

        assertSame(actualTeams.size(), expectedTeams.size());
        assertTrue(actualTeams.containsAll(expectedTeams));
    }

    @Test
    public void shouldContainTeamsFromCoachesAndPlayers() throws Exception {
        Set<String> expectedTeams = createSomeTeams();
        expectedTeams.add("Team D");

        when(stubbedPlayerService.getPlayers()).thenReturn(createSomePlayers());
        when(stubbedCoachService.getCoaches()).thenReturn(createSomeCoaches());

        Set<String> actualTeams = new TeamService(stubbedPlayerService, stubbedCoachService).getTeams();

        assertSame(actualTeams.size(), expectedTeams.size());
        assertTrue(actualTeams.containsAll(expectedTeams));
    }

    private Set<String> createSomeTeams() {
        HashSet<String> teams = new HashSet<String>();
        teams.add("Team A");
        teams.add("Team B");
        teams.add("Team C");
        return teams;
    }

    private HashSet<Coach> createSomeCoaches() {
        HashSet<Coach> coaches = new HashSet<Coach>();
        coaches.add(new CoachBuilder().withName("A").withTeam("Team B").build());
        coaches.add(new CoachBuilder().withName("B").withTeam("Team C").build());
        coaches.add(new CoachBuilder().withName("C").withTeam("Team D").build());
        return coaches;
    }

    private HashSet<Player> createSomePlayers() {
        HashSet<Player> players = new HashSet<Player>();
        players.add(new PlayerBuilder().withName("A").withTeam("Team A").build());
        players.add(new PlayerBuilder().withName("B").withTeam("Team B").build());
        players.add(new PlayerBuilder().withName("C").withTeam("Team C").build());
        return players;
    }
}
