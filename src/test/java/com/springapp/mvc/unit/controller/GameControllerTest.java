package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.GameController;
import com.springapp.mvc.service.TeamService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameControllerTest {
    @Test
    public void shouldPassRecordGameView() throws Exception {
        assertEquals("recordGame", new GameController(mock(TeamService.class)).showRecordGameForm().getViewName());
    }

    @Test
    public void shouldGetTeamsFromTeamService() throws Exception {
        TeamService mockTeamService = mock(TeamService.class);

        new GameController(mockTeamService).showRecordGameForm();

        verify(mockTeamService).getTeams();
    }
}
