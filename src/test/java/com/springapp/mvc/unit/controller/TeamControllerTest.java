package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.TeamController;
import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TeamControllerTest {

    @Mock
    private ModelMap mockedModelMap;
    @Mock
    private PlayerService stubbedPlayerService;
    @Mock
    private CoachService stubbedCoachService;
    private TeamController teamController;

    @Before
    public void setUp() {
        initMocks(this);
        teamController = new TeamController(stubbedPlayerService, stubbedCoachService);
    }

    @Test
    public void shouldDisplayTeamView() {
        assertEquals("team", teamController.showTeam(mockedModelMap, "Giants").getViewName());
    }

    @Test
    public void shouldPassTeamNameToView() {
        mockedModelMap = mock(ModelMap.class);
        teamController.showTeam(mockedModelMap, "Giants");
        verify(mockedModelMap).addAttribute("teamName", "Giants");
    }

    @Test
    public void shouldGetFilteredPlayerListFromPlayerService() {
        PlayerService mockedPlayerService = stubbedPlayerService;

        teamController.showTeam(mockedModelMap, "Rockets");

        verify(mockedPlayerService).getPlayersFrom("Rockets");
    }

    @Test
    public void shouldGetFilteredCoachListFromPlayerService() {
        CoachService mockedCoachService = stubbedCoachService;

        teamController.showTeam(mockedModelMap, "Rockets");

        verify(mockedCoachService).getCoachesFrom("Rockets");
    }

    @Test
    public void shouldPassPlayerListToView() {
        List<Player> playerList = new ArrayList<Player>();
        when(stubbedPlayerService.getPlayerList()).thenReturn(playerList);

        teamController.showTeam(mockedModelMap, "Giants");

        verify(mockedModelMap).addAttribute("playerList", playerList);
    }

    @Test
    public void shouldPassCoachListToView() {
        List<Coach> coachList = new ArrayList<Coach>();
        when(stubbedCoachService.getCoachList()).thenReturn(coachList);

        teamController.showTeam(mockedModelMap, "Team Name from Params");

        verify(mockedModelMap).addAttribute("coachList", coachList);

    }
}
