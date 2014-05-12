package com.springapp.mvc.integration;

import com.springapp.mvc.controller.TeamController;
import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.CoachBuilder;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TeamFiltrationTest {
    private CoachService coachService;
    private PlayerService playerService;
    private TeamController teamController;
    private ModelMap mockModelMap;

    @Before
    public void setUp() {
        coachService = new CoachService();
        playerService = new PlayerService();
        teamController = new TeamController(playerService, coachService);
        mockModelMap = mock(ModelMap.class);
    }

    @Test
    public void shouldPassFilteredPlayerListToView() {
        Player playerAubrey = new PlayerBuilder().withName("Aubrey")
                .withTeam("Rockets")
                .build();
        Player playerWhitney = new PlayerBuilder().withName("Whitney")
                .withTeam("Mockets")
                .build();
        HashSet<Player> playerList = new HashSet<Player>();
        playerList.add(playerAubrey);
        playerList.add(playerWhitney);
        playerService.setPlayerList(playerList);
        coachService.setCoachList(new HashSet<Coach>());

        HashSet<Player> filteredPlayerList = new HashSet<Player>();
        filteredPlayerList.add(playerAubrey);

        teamController.showTeam(mockModelMap, "Rockets");

        verify(mockModelMap).addAttribute("teamName", "Rockets");
        verify(mockModelMap).addAttribute("playerList", filteredPlayerList);
    }

    @Test
    public void shouldPassFilteredCoachListToView() {
        Coach coachCubs = new CoachBuilder().withName("Cubs Coach")
                .withTeam("Cubs").build();
        Coach coachYankees = new CoachBuilder().withName("Yankees Coach")
                .withTeam("Yankees").build();
        Set<Coach> coachList = new HashSet<Coach>();
        coachList.add(coachCubs);
        coachList.add(coachYankees);
        coachService.setCoachList(coachList);

        Set<Coach> filteredCoachList = new HashSet<Coach>();
        filteredCoachList.add(coachCubs);

        teamController.showTeam(mockModelMap, "Cubs");

        verify(mockModelMap).addAttribute("teamName", "Cubs");
        verify(mockModelMap).addAttribute("playerList", new HashSet<Player>());
        verify(mockModelMap).addAttribute("coachList", filteredCoachList);
    }
}
