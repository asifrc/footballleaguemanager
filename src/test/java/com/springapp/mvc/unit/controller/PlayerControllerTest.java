package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.PlayerController;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PlayerControllerTest {
    @Mock
    PlayerService stubbedPlayerService;
    @Mock
    ModelMap mockedModelMap;

    private PlayerController controller;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new PlayerController(stubbedPlayerService);
    }

    @Test
    public void shouldGetPlayersFromPlayerService() throws Exception {
        controller.listPlayers(mockedModelMap);
        verify(stubbedPlayerService).getPlayerList();
    }

    @Test
    public void shouldAddAttributetoModel() throws Exception {
        List<Player> players = new ArrayList<Player>();
        when(stubbedPlayerService.getPlayerList()).thenReturn(players);

        controller.listPlayers(mockedModelMap);

        verify(mockedModelMap).addAttribute("playerList", players);
    }
}