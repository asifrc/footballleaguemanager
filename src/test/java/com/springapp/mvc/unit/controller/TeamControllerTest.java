package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.TeamController;
import com.springapp.mvc.model.Player;
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
    private ModelMap mockModelMap;
    @Mock
    private PlayerService stubbedPlayerService;
    private TeamController teamController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        teamController = new TeamController(stubbedPlayerService);
    }

    @Test
    public void shouldDisplayTeamView() throws Exception {
        ModelMap mockModelMap = mock(ModelMap.class);
        assertEquals("team", teamController.showTeam(mockModelMap, "Giants"));
    }

    @Test
    public void shouldPassTeamNameToView() throws Exception {
        mockModelMap = mock(ModelMap.class);
        teamController.showTeam(mockModelMap, "Giants");
        verify(mockModelMap).addAttribute("teamName", "Giants");
    }

    @Test
    public void shouldPassPlayerListToView() throws Exception {
        List<Player> playerList = new ArrayList<Player>();
        when(stubbedPlayerService.getPlayerList()).thenReturn(playerList);

        teamController.showTeam(mockModelMap, "Giants");

        verify(mockModelMap).addAttribute("playerList", playerList);
    }


}
