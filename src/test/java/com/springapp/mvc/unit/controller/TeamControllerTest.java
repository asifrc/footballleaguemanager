package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.TeamController;
import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.GameService;
import com.springapp.mvc.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TeamControllerTest {

    @Mock private ModelMap mockedModelMap;
    @Mock private PlayerService stubbedPlayerService;
    @Mock private CoachService stubbedCoachService;
    @Mock private GameService mockedGameService;
    private TeamController teamController;

    @Before
    public void setUp() {
        initMocks(this);
        teamController = new TeamController(stubbedPlayerService, stubbedCoachService, mockedGameService);
    }

    @Test
    public void shouldDisplayTeamView() {
        assertEquals("team", teamController.showTeam(mockedModelMap, "Giants").getViewName());
    }

    @Test
    public void shouldPassTeamNameToView() {
        teamController.showTeam(mockedModelMap, "Giants");
        verify(mockedModelMap).addAttribute("teamName", "Giants");
    }

    @Test
    public void shouldPassTeamRecordToView() {
        teamController.showTeam(mockedModelMap, "Giants");
        verify(mockedModelMap).addAttribute(eq("record"), anyString());
    }

    @Test
    public void shouldGetTeamRecordFromGameService() {
        teamController.showTeam(mockedModelMap, "Giants");
        verify(mockedGameService).getWinLossTieRecordFor("Giants");
    }


    @Test
    public void shouldGetFilteredPlayersFromPlayerService() {
        PlayerService mockedPlayerService = stubbedPlayerService;

        teamController.showTeam(mockedModelMap, "Rockets");

        verify(mockedPlayerService).getPlayersFrom("Rockets");
    }

    @Test
    public void shouldGetFilteredCoachesFromCoachService() {
        CoachService mockedCoachService = stubbedCoachService;

        teamController.showTeam(mockedModelMap, "Rockets");

        verify(mockedCoachService).getCoachesFrom("Rockets");
    }

    @Test
    public void shouldPassPlayersToView() {
        Set<Player> players = new HashSet<Player>();
        when(stubbedPlayerService.getPlayers()).thenReturn(players);

        teamController.showTeam(mockedModelMap, "Giants");

        verify(mockedModelMap).addAttribute("players", players);
    }

    @Test
    public void shouldPassCoachesToView() {
        Set<Coach> coaches = new HashSet<Coach>();
        when(stubbedCoachService.getCoaches()).thenReturn(coaches);

        teamController.showTeam(mockedModelMap, "Team Name from Params");

        verify(mockedModelMap).addAttribute("coaches", coaches);
    }
}
