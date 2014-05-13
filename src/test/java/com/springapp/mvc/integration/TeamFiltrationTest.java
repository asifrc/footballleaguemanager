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
    public void shouldPassFilteredPlayersToView() {
        Player playerAubrey = new PlayerBuilder().withName("Aubrey")
                .withTeam("Rockets")
                .build();
        Player playerWhitney = new PlayerBuilder().withName("Whitney")
                .withTeam("Mockets")
                .build();
        HashSet<Player> players = new HashSet<Player>();
        players.add(playerAubrey);
        players.add(playerWhitney);
        playerService.setPlayers(players);
        coachService.setCoaches(new HashSet<Coach>());

        HashSet<Player> filteredPlayers = new HashSet<Player>();
        filteredPlayers.add(playerAubrey);

        teamController.showTeam(mockModelMap, "Rockets");

        verify(mockModelMap).addAttribute("teamName", "Rockets");
        verify(mockModelMap).addAttribute("players", filteredPlayers);
    }

    @Test
    public void shouldPassFilteredCoachesToView() {
        Coach coachCubs = new CoachBuilder().withName("Cubs Coach")
                .withTeam("Cubs").build();
        Coach coachYankees = new CoachBuilder().withName("Yankees Coach")
                .withTeam("Yankees").build();
        Set<Coach> coaches = new HashSet<Coach>();
        coaches.add(coachCubs);
        coaches.add(coachYankees);
        coachService.setCoaches(coaches);

        Set<Coach> filteredCoaches = new HashSet<Coach>();
        filteredCoaches.add(coachCubs);

        teamController.showTeam(mockModelMap, "Cubs");

        verify(mockModelMap).addAttribute("teamName", "Cubs");
        verify(mockModelMap).addAttribute("players", new HashSet<Player>());
        verify(mockModelMap).addAttribute("coaches", filteredCoaches);
    }
}
