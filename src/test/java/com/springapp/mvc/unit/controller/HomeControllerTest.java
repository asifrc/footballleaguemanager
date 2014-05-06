package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.HomeController;
import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.CoachBuilder;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.CoachService;
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

public class HomeControllerTest {
    @Mock PlayerService stubbedPlayerService;
    @Mock CoachService stubbedCoachService;
    @Mock ModelMap mockedModelMap;

    private HomeController controller;
    private List<Player> players;
    private List<Coach> coaches;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new HomeController(stubbedPlayerService, stubbedCoachService);

        players = new ArrayList<Player>();
        players.add(new PlayerBuilder().withName("Bob").withNumber("0").build());
        players.add(new PlayerBuilder().withName("Sally").withNumber("1").build());

        coaches = new ArrayList<Coach>();
        coaches.add(new CoachBuilder().withName("Jack").build());
        coaches.add(new CoachBuilder().withName("Jill").build());

        when(stubbedPlayerService.getPlayerList()).thenReturn(players);
        when(stubbedCoachService.getCoachList()).thenReturn(coaches);
    }

    @Test
    public void shouldGetPlayersFromPlayerServiceWhenListingPlayers() throws Exception {
        controller.listPlayersAndCoaches(mockedModelMap);
        verify(stubbedPlayerService).getPlayerList();
    }

    @Test
    public void shouldGetCoachesFromCoachServiceWhenListingCoaches() throws Exception {
        controller.listPlayersAndCoaches(mockedModelMap);
        verify(stubbedCoachService).getCoachList();
    }

    @Test
    public void shouldAddAttributeToModelWhenListingPlayers() throws Exception {
        controller.listPlayersAndCoaches(mockedModelMap);

        verify(mockedModelMap).addAttribute("playerList", players);
    }

    @Test
    public void shouldPassAPlayerToTheViewWhenFindingAPlayerWithMatchingParameters() throws Exception {
        Player player = new PlayerBuilder().withName("Ayanga").withNumber("70").build();
        when(stubbedPlayerService.findPlayerByName("Ayanga")).thenReturn(player);

        ModelAndView modelAndView = controller.findPlayer(player);
        Player foundPlayer = (Player) modelAndView.getModelMap().get("foundPlayer");

        assertEquals(player, foundPlayer);
        assertFalse(modelAndView.getModelMap().containsKey("error"));
    }

    @Test
    public void shouldPassAnErrorToTheViewWhenFindingAPlayerWithNoMatches() throws Exception {
        when(stubbedPlayerService.findPlayerByName(anyString())).thenReturn(null);

        Player player = new PlayerBuilder().withName("NonMatchingPlayer").withNumber("91239").build();
        ModelAndView modelAndView = controller.findPlayer(player);

        assertTrue(modelAndView.getModelMap().containsKey("error"));
    }
}