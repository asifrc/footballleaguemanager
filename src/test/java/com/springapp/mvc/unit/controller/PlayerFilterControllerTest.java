package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.PlayerFilterController;
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

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PlayerFilterControllerTest {
    @Mock
    PlayerService stubbedPlayerService;
    @Mock
    CoachService stubbedCoachService;
    @Mock
    ModelMap mockedModelMap;

    private PlayerFilterController controller;
    private Set<Player> players;
    private Set<Coach> coaches;

    @Before
    public void setUp() {
        initMocks(this);
        controller = new PlayerFilterController(stubbedPlayerService, stubbedCoachService);

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
    public void shouldRenderFilteredPlayerListAfterFilteringPlayers() {
        ModelAndView modelAndView = controller.filterPlayers(mockedModelMap, "18");

        assertTrue(modelAndView.getViewName().equals("filteredPlayers"));
    }

}
