package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.GameController;
import com.springapp.mvc.model.Game;
import com.springapp.mvc.service.GameService;
import com.springapp.mvc.service.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameControllerTest {

    @Mock private TeamService mockTeamService;
    @Mock private GameService mockGameService;
    private GameController gameController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        gameController = new GameController(mockTeamService, mockGameService);
    }

    @Test
    public void shouldPassRecordGameView() throws Exception {
        assertEquals("recordGame", gameController.showRecordGameForm().getViewName());
    }

    @Test
    public void shouldGetTeamsFromTeamService() throws Exception {
        gameController.showRecordGameForm();

        verify(mockTeamService).getTeams();
    }

    @Test
    public void shouldAddGameWhenGameIsRecorded() throws Exception {
        Game game = new Game("Winner", 10, "Loser", 9);

        gameController.handleRecordGameForm(game, mock(RedirectAttributes.class));

        verify(mockGameService).addGame(game);
    }
}
