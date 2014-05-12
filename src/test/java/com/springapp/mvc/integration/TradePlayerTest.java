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
    public void shouldPassPlayerListToView() {
        PlayerService playerService = new PlayerService();
        CoachService coachService = new CoachService();
        TeamService teamService = new TeamService(playerService, coachService);
        TradeController tradeController = new TradeController(playerService, teamService);
        Set<Player> playerList = new HashSet<Player>();
        playerList.add(new PlayerBuilder().build());

        playerService.setPlayerList(playerList);

        ModelAndView modelAndView = tradeController.showTradePlayers(new ModelMap());

        assertEquals(playerList, (Set<Player>) modelAndView.getModelMap().get("playerList"));
    }

    @Test
    public void shouldPassTeamListToTradeTeamView() throws Exception {
        PlayerService playerService = new PlayerService();
        CoachService coachService = new CoachService();
        TeamService teamService = new TeamService(playerService, coachService);

        playerService.setPlayerList(createListOfPlayers());
        coachService.setCoachList(createListOfCoaches());

        Set<String> expectedTeamList = createListOfTeams();

        TradeController tradeController = new TradeController(playerService, teamService);
        ModelAndView modelAndView = tradeController.showTradePlayers(new ModelMap());

        Collection<String> actualTeamList = (Set<String>) modelAndView.getModelMap().get("teamList");
        assertTrue(actualTeamList.containsAll(expectedTeamList));
    }

    private Set<String> createListOfTeams() {
        HashSet<String> teams = new HashSet<String>();
        teams.add("Team A");
        teams.add("Team B");
        teams.add("Team C");
        teams.add("Team D");
        return teams;
    }

    private HashSet<Coach> createListOfCoaches() {
        HashSet<Coach> coachList = new HashSet<Coach>();
        coachList.add(new CoachBuilder().withName("A").withTeam("Team B").build());
        coachList.add(new CoachBuilder().withName("B").withTeam("Team C").build());
        coachList.add(new CoachBuilder().withName("C").withTeam("Team D").build());
        return coachList;
    }

    private HashSet<Player> createListOfPlayers() {
        HashSet<Player> playerList = new HashSet<Player>();
        playerList.add(new PlayerBuilder().withName("A").withTeam("Team A").build());
        playerList.add(new PlayerBuilder().withName("B").withTeam("Team B").build());
        playerList.add(new PlayerBuilder().withName("C").withTeam("Team C").build());
        return playerList;
    }
}
