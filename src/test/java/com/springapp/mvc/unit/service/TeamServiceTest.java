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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
        Collection<String> expectedTeamList = Arrays.asList("Team A", "Team B", "Team C");
        when(stubbedPlayerService.getPlayerList()).thenReturn(createListOfPlayers());

        Collection<String> actualTeamList = new TeamService(stubbedPlayerService, stubbedCoachService).getTeamList();

        assertSame(actualTeamList.size(), expectedTeamList.size());
        assertTrue(actualTeamList.containsAll(expectedTeamList));
    }

    @Test
    public void shouldNotContainDuplicateTeamsWhenThereAreMultiplePlayersFromTheSameTeam() throws Exception {
        ArrayList<Player> listOfPlayers = createListOfPlayers();
        listOfPlayers.add(new PlayerBuilder().withName("D").withTeam("Team B").build());

        Collection<String> expectedTeamList = Arrays.asList("Team A", "Team B", "Team C");
        when(stubbedPlayerService.getPlayerList()).thenReturn(listOfPlayers);

        Collection<String> actualTeamList = new TeamService(stubbedPlayerService, stubbedCoachService).getTeamList();

        assertSame(actualTeamList.size(), expectedTeamList.size());
        assertTrue(actualTeamList.containsAll(expectedTeamList));
    }

    @Test
    public void shouldContainTeamsFromCoachesAndPlayers() throws Exception {
        Collection<String> expectedTeamList = Arrays.asList("Team A", "Team B", "Team C", "Team D");

        when(stubbedPlayerService.getPlayerList()).thenReturn(createListOfPlayers());
        when(stubbedCoachService.getCoachList()).thenReturn(createListOfCoaches());

        Collection<String> actualTeamList = new TeamService(stubbedPlayerService, stubbedCoachService).getTeamList();

        assertSame(actualTeamList.size(), expectedTeamList.size());
        assertTrue(actualTeamList.containsAll(expectedTeamList));
    }

    private ArrayList<Coach> createListOfCoaches() {
        ArrayList<Coach> coachList = new ArrayList<Coach>();
        coachList.add(new CoachBuilder().withName("A").withTeam("Team B").build());
        coachList.add(new CoachBuilder().withName("B").withTeam("Team C").build());
        coachList.add(new CoachBuilder().withName("C").withTeam("Team D").build());
        return coachList;
    }

    private ArrayList<Player> createListOfPlayers() {
        ArrayList<Player> playerList = new ArrayList<Player>();
        playerList.add(new PlayerBuilder().withName("A").withTeam("Team A").build());
        playerList.add(new PlayerBuilder().withName("B").withTeam("Team B").build());
        playerList.add(new PlayerBuilder().withName("C").withTeam("Team C").build());
        return playerList;
    }
}
