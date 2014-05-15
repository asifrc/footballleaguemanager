package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.TradeController;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.TeamService;
import com.springapp.mvc.service.TradeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class TradeControllerTest {

    @Mock PlayerService mockedPlayerService;
    @Mock TeamService mockedTeamService;
    @Mock ModelMap mockedModelMap;
    @Mock TradeService mockedTradeService;

    TradeController tradeController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        tradeController = new TradeController(mockedPlayerService, mockedTeamService, mockedTradeService);
    }

    @Test
    public void shouldDisplayTradePlayerView() throws Exception {
        assertEquals("tradePlayers", tradeController.renderTradePlayersView(new ModelMap()).getViewName());
    }

    @Test
    public void shouldGetPlayersFromPlayerService() throws Exception {
        tradeController.renderTradePlayersView(new ModelMap());
        verify(mockedPlayerService).getPlayers();
    }

    @Test
    public void shouldPassPlayersToView() throws Exception {
        tradeController.renderTradePlayersView(mockedModelMap);
        verify(mockedModelMap).addAttribute(eq("players"), anySetOf(Player.class));
    }

    @Test
    public void shouldGetTeamsFromTeamService() throws Exception {
        tradeController.renderTradePlayersView(new ModelMap());
        verify(mockedTeamService).getTeams();
    }

    @Test
    public void shouldPassTeamsToView() throws Exception {
        tradeController.renderTradePlayersView(mockedModelMap);
        verify(mockedModelMap).addAttribute(eq("teams"), anySetOf(String.class));
    }

    @Test
    public void shouldRedirectToHomeAfterHandlingGoodTrade() throws Exception {
        ModelAndView modelAndView = tradeController.handleTradeRequest(
                new ArrayList<String>(),
                new ArrayList<String>(),
                new ArrayList<String>(),
                new ArrayList<String>(),
                new ArrayList<String>());
        assertEquals("redirect:/", modelAndView.getViewName());
    }

    @Test
    public void shouldInteractWithTradeServiceToTradePlayers() throws Exception {
        List<String> names = new ArrayList<String>();
        List<String> currentTeams = new ArrayList<String>();
        List<String> numbers = new ArrayList<String>();
        List<String> ages = new ArrayList<String>();
        List<String> newTeams = new ArrayList<String>();

        tradeController.handleTradeRequest(
                names, currentTeams, numbers, ages, newTeams);

        verify(mockedTradeService).tradePlayers(names, currentTeams, numbers, ages, newTeams);
    }
}
