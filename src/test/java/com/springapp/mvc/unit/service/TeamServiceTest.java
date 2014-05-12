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
    public void shouldGetTeamsFromPlayerList() throws Exception {
        Set<String> expectedTeamList = createListOfTeams();
        when(stubbedPlayerService.getPlayerList()).thenReturn(createListOfPlayers());

        Set<String> actualTeamList = new TeamService(stubbedPlayerService, stubbedCoachService).getTeamList();

        assertSame(actualTeamList.size(), expectedTeamList.size());
        assertTrue(actualTeamList.containsAll(expectedTeamList));
    }

    @Test
    public void shouldNotContainDuplicateTeamsWhenThereAreMultiplePlayersFromTheSameTeam() throws Exception {
        HashSet<Player> listOfPlayers = createListOfPlayers();
        listOfPlayers.add(new PlayerBuilder().withName("D").withTeam("Team B").build());

        Set<String> expectedTeamList = createListOfTeams();
        when(stubbedPlayerService.getPlayerList()).thenReturn(listOfPlayers);

        Set<String> actualTeamList = new TeamService(stubbedPlayerService, stubbedCoachService).getTeamList();

        assertSame(actualTeamList.size(), expectedTeamList.size());
        assertTrue(actualTeamList.containsAll(expectedTeamList));
    }

    @Test
    public void shouldContainTeamsFromCoachesAndPlayers() throws Exception {
        Set<String> expectedTeamList = createListOfTeams();
        expectedTeamList.add("Team D");

        when(stubbedPlayerService.getPlayerList()).thenReturn(createListOfPlayers());
        when(stubbedCoachService.getCoachList()).thenReturn(createListOfCoaches());

        Set<String> actualTeamList = new TeamService(stubbedPlayerService, stubbedCoachService).getTeamList();

        assertSame(actualTeamList.size(), expectedTeamList.size());
        assertTrue(actualTeamList.containsAll(expectedTeamList));
    }

    private Set<String> createListOfTeams() {
        HashSet<String> teams = new HashSet<String>();
        teams.add("Team A");
        teams.add("Team B");
        teams.add("Team C");
        return teams;
    }

    private HashSet<Coach> createListOfCoaches() {
        HashSet<Coach> coachList = new HashSet<Coach>();
        coachList.add(new CoachBuilder().withName("A").withTeam("Team B").build());
        coachList.add(new CoachBuilder().withName("B").withTeam("Team C").build());
        coachList.add(new CoachBuilder().withName("C").withTeam("Team D").build());
        return coachList;
    }

    private HashSet<Player> createListOfPlayers() {
        HashSet<Player> playerList = new HashSet<Player>();
        playerList.add(new PlayerBuilder().withName("A").withTeam("Team A").build());
        playerList.add(new PlayerBuilder().withName("B").withTeam("Team B").build());
        playerList.add(new PlayerBuilder().withName("C").withTeam("Team C").build());
        return playerList;
    }
}
