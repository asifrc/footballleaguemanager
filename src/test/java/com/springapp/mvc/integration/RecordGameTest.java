package com.springapp.mvc.integration;

import com.springapp.mvc.controller.GameController;
import com.springapp.mvc.model.*;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.GameService;
import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class RecordGameTest {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void getRecordShouldRenderRecordGameView() throws Exception {
        mockMvc.perform(get("/record"))
                .andExpect(status().isOk())
                .andExpect(view().name("recordGame"));
    }

    @Test
    public void shouldPassTeamsToView() throws Exception {
        PlayerService playerService = new PlayerService();
        CoachService coachService = new CoachService();
        GameService gameService = new GameService();
        TeamService teamService = new TeamService(playerService, coachService);
        GameController gameController = new GameController(teamService, gameService);

        playerService.setPlayers(createSomePlayers());
        coachService.setCoaches(createSomeCoaches());

        ModelAndView modelAndView = gameController.showRecordGameForm();
        assertEquals(teamService.getTeams(), modelAndView.getModelMap().get("teams"));
    }

    @Test
    public void shouldAddGameToGameServiceWhenResultsAreSubmitted() {
        PlayerService playerService = new PlayerService();
        CoachService coachService = new CoachService();
        TeamService teamService = new TeamService(playerService, coachService);
        GameService gameService = new GameService();
        GameController gameController = new GameController(teamService, gameService);

        Game game1 = new Game("Winner", 10, "Loser", 9);
        Game game2 = new Game("Winner", 10, "Loser", 9);

        gameController.handleRecordGameForm(game1, mock(RedirectAttributes.class));
        gameController.handleRecordGameForm(game2, mock(RedirectAttributes.class));

        assertEquals("2-0-0", gameService.getWinLossTieRecordFor("Winner"));
    }

    private HashSet<Coach> createSomeCoaches() {
        HashSet<Coach> coaches = new HashSet<Coach>();
        coaches.add(new CoachBuilder().withName("A").withTeam("Team B").build());
        coaches.add(new CoachBuilder().withName("B").withTeam("Team C").build());
        coaches.add(new CoachBuilder().withName("C").withTeam("Team D").build());
        return coaches;
    }

    private HashSet<Player> createSomePlayers() {
        HashSet<Player> players = new HashSet<Player>();
        players.add(new PlayerBuilder().withName("A").withTeam("Team A").build());
        players.add(new PlayerBuilder().withName("B").withTeam("Team B").build());
        players.add(new PlayerBuilder().withName("C").withTeam("Team C").build());
        return players;
    }
}
