package com.springapp.mvc.integration;

import com.springapp.mvc.controller.TeamController;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.PlayerService;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TeamFiltrationTest {
    @Test
    public void shouldPassFilteredPlayerListToView() throws Exception {
        CoachService coachService = new CoachService();
        PlayerService playerService = new PlayerService();
        TeamController teamController = new TeamController(playerService, coachService);
        ArrayList<Player> playerList = new ArrayList<Player>();
        playerList.add(new PlayerBuilder().withName("Aubrey")
                .withTeam("Rockets")
                .build());
        playerList.add(new PlayerBuilder().withName("Whitney")
                .withTeam("Mockets")
                .build());

        playerService.setPlayerList(playerList);

        ModelMap mockModelMap = mock(ModelMap.class);
        teamController.showTeam(mockModelMap, "Rockets");

        ArrayList<Player> expectedPlayerList = new ArrayList<Player>();
        expectedPlayerList.add(new PlayerBuilder()
                .withName("Aubrey")
                .withTeam("Rockets")
                .build()
        );

        verify(mockModelMap).addAttribute("teamName", "Rockets");
        verify(mockModelMap).addAttribute("playerList", expectedPlayerList);
    }
}
