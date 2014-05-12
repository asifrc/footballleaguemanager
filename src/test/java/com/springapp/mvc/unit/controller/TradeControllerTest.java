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
import static org.mockito.Matchers.anyListOf;
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
    public void shouldGetListOfPlayersFromPlayerService() throws Exception {
        tradeController.showTradePlayers(new ModelMap());
        verify(mockedPlayerService).getPlayerList();
    }

    @Test
    public void shouldPassPlayerListToView() throws Exception {
        tradeController.showTradePlayers(mockedModelMap);
        verify(mockedModelMap).addAttribute(eq("playerList"), anyListOf(Player.class));
    }

    @Test
    public void shouldGetListOfTeamsFromTeamService() throws Exception {
        tradeController.showTradePlayers(new ModelMap());
        verify(mockedTeamService).getTeamList();
    }

    @Test
    public void shouldPassListOfTeamsToView() throws Exception {
        tradeController.showTradePlayers(mockedModelMap);
        verify(mockedModelMap).addAttribute(eq("teamList"), anyListOf(String.class));
    }
}
