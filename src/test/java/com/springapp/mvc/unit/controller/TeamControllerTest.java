package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.TeamController;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TeamControllerTest {
    @Test
    public void shouldDisplayTeamView() throws Exception {
        ModelMap mockModelMap = mock(ModelMap.class);
        assertEquals("team", new TeamController().showTeam(mockModelMap, "Giants"));
    }

    @Test
    public void shouldPassTeamNameToView() throws Exception {
        ModelMap mockModelMap = mock(ModelMap.class);
        new TeamController().showTeam(mockModelMap, "Giants");
        verify(mockModelMap).addAttribute("teamName", "Giants");
    }
}
