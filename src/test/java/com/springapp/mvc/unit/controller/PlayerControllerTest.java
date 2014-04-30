package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.PlayerController;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PlayerControllerTest {
    @Mock
    PlayerService stubbedPlayerService;
    @Mock
    ModelMap mockedModelMap;

    private PlayerController controller;
    private List<Player> players;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new PlayerController(stubbedPlayerService);

        players = new ArrayList<Player>();
        players.add(new Player("Bob", "0"));
        players.add(new Player("Sally", "1"));

        when(stubbedPlayerService.getPlayerList()).thenReturn(players);
    }

    @Test
    public void shouldGetPlayersFromPlayerServiceWhenListingPlayers() throws Exception {
        controller.listPlayers(mockedModelMap);
        verify(stubbedPlayerService).getPlayerList();
    }

    @Test
    public void shouldAddAttributeToModelWhenListingPlayers() throws Exception {
        controller.listPlayers(mockedModelMap);

        verify(mockedModelMap).addAttribute("playerList", players);
    }

    @Test
    public void shouldPassAPlayerToTheViewWhenFindingAPlayerWithMatchingParameters() throws Exception {
        Player player = new Player("Ayanga", "70");
        when(stubbedPlayerService.findPlayer("Ayanga", "70")).thenReturn(player);

        ModelAndView modelAndView = controller.findPlayer(player);
        Player foundPlayer = (Player) modelAndView.getModelMap().get("player");

        assertEquals(player, foundPlayer);
        assertFalse(modelAndView.getModelMap().containsKey("error"));
    }

    @Test
    public void shouldPassAnErrorToTheViewWhenFindingAPlayerWithNoMatches() throws Exception {
        when(stubbedPlayerService.findPlayer(anyString(), anyString())).thenReturn(null);

        Player player = new Player("NonMatchingPlayer", "91239");
        ModelAndView modelAndView = controller.findPlayer(player);

        assertTrue(modelAndView.getModelMap().containsKey("error"));
    }
}