package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.PlayerFinderController;
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

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PlayerFinderControllerTest {
    @Mock
    private PlayerService stubbedPlayerService;
    @Mock
    private CoachService stubbedCoachService;
    @Mock
    private ModelMap mockedModelMap;

    private PlayerFinderController controller;
    private Set<Player> players;
    private Set<Coach> coaches;

    @Before
    public void setUp() {
        initMocks(this);
        controller = new PlayerFinderController(stubbedPlayerService, stubbedCoachService);

        players = new HashSet<Player>();
        players.add(new PlayerBuilder().withName("Bob").withNumber("0").build());
        players.add(new PlayerBuilder().withName("Sally").withNumber("1").build());

        coaches = new HashSet<Coach>();
        coaches.add(new CoachBuilder().withName("Jack").build());
        coaches.add(new CoachBuilder().withName("Jill").build());

        when(stubbedPlayerService.getPlayers()).thenReturn(players);
        when(stubbedCoachService.getCoaches()).thenReturn(coaches);
    }

    @Test
    public void shouldPassAPlayerToTheViewWhenFindingAPlayerWithMatchingParameters() {
        Player player = new PlayerBuilder().withName("Ayanga").withNumber("70").build();
        when(stubbedPlayerService.findPlayerByName("Ayanga")).thenReturn(player);

        ModelAndView modelAndView = controller.findPlayer(player);
        Player foundPlayer = (Player) modelAndView.getModelMap().get("player");

        assertEquals(player, foundPlayer);
        assertFalse(modelAndView.getModelMap().containsKey("error"));
    }

    @Test
    public void shouldPassAnErrorToTheViewWhenFindingAPlayerWithNoMatches() {
        when(stubbedPlayerService.findPlayerByName(anyString())).thenReturn(null);
        Player player = new PlayerBuilder().withName("NonMatchingPlayer").withNumber("91239").build();

        ModelAndView modelAndView = controller.findPlayer(player);

        assertTrue(modelAndView.getModelMap().containsKey("error"));
    }
}
