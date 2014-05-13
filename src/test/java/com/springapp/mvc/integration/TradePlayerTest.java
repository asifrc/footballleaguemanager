package com.springapp.mvc.integration;

import com.springapp.mvc.controller.TradeController;
import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.CoachBuilder;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.TeamService;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TradePlayerTest {
    @Test
    public void shouldPassPlayersToView() {
        PlayerService playerService = new PlayerService();
        CoachService coachService = new CoachService();
        TeamService teamService = new TeamService(playerService, coachService);
        TradeController tradeController = new TradeController(playerService, teamService);
        Set<Player> players = new HashSet<Player>();
        players.add(new PlayerBuilder().build());

        playerService.setPlayers(players);

        ModelAndView modelAndView = tradeController.showTradePlayers(new ModelMap());

        assertEquals(players, (Set<Player>) modelAndView.getModelMap().get("players"));
    }

    @Test
    public void shouldPassTeamsToTradeTeamView() throws Exception {
        PlayerService playerService = new PlayerService();
        CoachService coachService = new CoachService();
        TeamService teamService = new TeamService(playerService, coachService);

        playerService.setPlayers(createSomePlayers());
        coachService.setCoaches(createSomeCoaches());

        Set<String> expectedTeams = createSomeTeams();

        TradeController tradeController = new TradeController(playerService, teamService);
        ModelAndView modelAndView = tradeController.showTradePlayers(new ModelMap());

        Collection<String> actualTeams = (Set<String>) modelAndView.getModelMap().get("teams");
        assertTrue(actualTeams.containsAll(expectedTeams));
    }

    private Set<String> createSomeTeams() {
        HashSet<String> teams = new HashSet<String>();
        teams.add("Team A");
        teams.add("Team B");
        teams.add("Team C");
        teams.add("Team D");
        return teams;
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
