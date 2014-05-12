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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TradePlayerTest {
    @Test
    public void shouldPassPlayerListToView() {
        PlayerService playerService = new PlayerService();
        CoachService coachService = new CoachService();
        TeamService teamService = new TeamService(playerService, coachService);
        TradeController tradeController = new TradeController(playerService, teamService);
        List<Player> playerList = new ArrayList<Player>();
        playerList.add(new PlayerBuilder().build());

        playerService.setPlayerList(playerList);

        ModelAndView modelAndView = tradeController.showTradePlayers(new ModelMap());

        assertEquals(playerList, (List<Player>) modelAndView.getModelMap().get("playerList"));
    }

    @Test
    public void shouldPassTeamListToTradeTeamView() throws Exception {
        PlayerService playerService = new PlayerService();
        CoachService coachService = new CoachService();
        TeamService teamService = new TeamService(playerService, coachService);

        playerService.setPlayerList(createListOfPlayers());
        coachService.setCoachList(createListOfCoaches());

        List<String> expectedTeamList = createListOfTeams();

        TradeController tradeController = new TradeController(playerService, teamService);
        ModelAndView modelAndView = tradeController.showTradePlayers(new ModelMap());

        Collection<String> actualTeamList = (Collection<String>) modelAndView.getModelMap().get("teamList");
        assertTrue(actualTeamList.containsAll(expectedTeamList));
    }

    private List<String> createListOfTeams() {
        return Arrays.asList("Team A", "Team B", "Team C", "Team D");
    }

    private ArrayList<Coach> createListOfCoaches() {
        ArrayList<Coach> coachList = new ArrayList<Coach>();
        coachList.add(new CoachBuilder().withName("A").withTeam("Team B").build());
        coachList.add(new CoachBuilder().withName("B").withTeam("Team C").build());
        coachList.add(new CoachBuilder().withName("C").withTeam("Team D").build());
        return coachList;
    }

    private ArrayList<Player> createListOfPlayers() {
        ArrayList<Player> playerList = new ArrayList<Player>();
        playerList.add(new PlayerBuilder().withName("A").withTeam("Team A").build());
        playerList.add(new PlayerBuilder().withName("B").withTeam("Team B").build());
        playerList.add(new PlayerBuilder().withName("C").withTeam("Team C").build());
        return playerList;
    }
}
