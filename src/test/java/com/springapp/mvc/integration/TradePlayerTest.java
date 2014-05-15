package com.springapp.mvc.integration;

import com.springapp.mvc.controller.TradeController;
import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.CoachBuilder;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.TeamService;
import com.springapp.mvc.service.TradeService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TradePlayerTest {

    private TradeController tradeController;
    private PlayerService playerService;
    private CoachService coachService;
    private TeamService teamService;
    private TradeService tradeService;

    @Before
    public void setUp() throws Exception {
        playerService = new PlayerService();
        coachService = new CoachService();
        teamService = new TeamService(playerService, coachService);
        tradeService = new TradeService(playerService);
        tradeController = new TradeController(playerService, teamService, tradeService);
    }

    @Test
    public void shouldPassPlayersToView() {
        Set<Player> players = new HashSet<Player>();
        players.add(new PlayerBuilder().build());

        playerService.setPlayers(players);

        ModelAndView modelAndView = tradeController.renderTradePlayersView(new ModelMap());

        assertEquals(players, (Set<Player>) modelAndView.getModelMap().get("players"));
    }

    @Test
    public void shouldPassTeamsToTradeTeamView() throws Exception {
        playerService.setPlayers(createSomePlayers());
        coachService.setCoaches(createSomeCoaches());

        Set<String> expectedTeams = createSomeTeams();

        ModelAndView modelAndView = tradeController.renderTradePlayersView(new ModelMap());

        Collection<String> actualTeams = (Set<String>) modelAndView.getModelMap().get("teams");
        assertTrue(actualTeams.containsAll(expectedTeams));
    }

    @Test
    public void shouldUpdatePlayerTeamsAfterATrade() throws Exception {
        playerService.setPlayers(createSomePlayers());
        Set<Player> expectedPlayers = createSomePlayers();  //mystery why this doesn't work...
//        Set<Player> expectedPlayers = playerService.getPlayers();

        Iterator<Player> playerIterator = expectedPlayers.iterator();
        Player player = playerIterator.next();
        player.setTeam("Team D");

        tradeController.handleTradeRequest(
                new ArrayList<String>(Arrays.asList("A", "B", "C")),
                new ArrayList<String>(Arrays.asList("Team A", "Team B", "Team C")),
                new ArrayList<String>(Arrays.asList("Default Number", "Default Number", "Default Number")),
                new ArrayList<String>(Arrays.asList("20", "20", "20")),
                new ArrayList<String>(Arrays.asList("Team D", "-- Trade --", "-- Trade --"))
        );

        Set<Player> actualPlayers = playerService.getPlayers();

        assertEquals(expectedPlayers.toString(), actualPlayers.toString());
    }

    private Set<String> createSomeTeams() {
        HashSet<String> teams = new HashSet<String>();
        teams.add("Team A");
        teams.add("Team B");
        teams.add("Team C");
        teams.add("Team D");
        return teams;
    }

    private Set<Coach> createSomeCoaches() {
        Set<Coach> coaches = new HashSet<Coach>();
        coaches.add(new CoachBuilder().withName("A").withTeam("Team B").build());
        coaches.add(new CoachBuilder().withName("B").withTeam("Team C").build());
        coaches.add(new CoachBuilder().withName("C").withTeam("Team D").build());
        return coaches;
    }

    private Set<Player> createSomePlayers() {
        Set<Player> players = new HashSet<Player>();
        players.add(new PlayerBuilder().withName("A").withTeam("Team A").build());
        players.add(new PlayerBuilder().withName("B").withTeam("Team B").build());
        players.add(new PlayerBuilder().withName("C").withTeam("Team C").build());
        return players;
    }
}
