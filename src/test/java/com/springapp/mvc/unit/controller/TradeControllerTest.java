package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.TradeController;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class TradeControllerTest {

    @Mock PlayerService mockedPlayerService;
    @Mock TeamService mockedTeamService;
    @Mock ModelMap mockedModelMap;

    TradeController tradeController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        tradeController = new TradeController(mockedPlayerService, mockedTeamService);
    }

    @Test
    public void shouldDisplayTradePlayerView() throws Exception {
        assertEquals("tradePlayers", tradeController.showTradePlayers(new ModelMap()).getViewName());
    }

    @Test
    public void shouldGetPlayersFromPlayerService() throws Exception {
        tradeController.showTradePlayers(new ModelMap());
        verify(mockedPlayerService).getPlayers();
    }

    @Test
    public void shouldPassPlayersToView() throws Exception {
        tradeController.showTradePlayers(mockedModelMap);
        verify(mockedModelMap).addAttribute(eq("players"), anySetOf(Player.class));
    }

    @Test
    public void shouldGetTeamsFromTeamService() throws Exception {
        tradeController.showTradePlayers(new ModelMap());
        verify(mockedTeamService).getTeams();
    }

    @Test
    public void shouldPassTeamsToView() throws Exception {
        tradeController.showTradePlayers(mockedModelMap);
        verify(mockedModelMap).addAttribute(eq("teams"), anySetOf(String.class));
    }
}
