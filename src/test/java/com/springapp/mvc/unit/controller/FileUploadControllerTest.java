package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.FileUploadController;
import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.CoachBuilder;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.FileUploadService;
import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileUploadControllerTest {
    @Mock PlayerService mockedPlayerService;
    @Mock CoachService mockedCoachService;
    @Mock MultipartFile mockedFile;
    @Mock FileUploadService stubbedFileUploadService;
    @Mock TeamService mockedTeamService;

    private FileUploadController fileUploadController;

    @Before
    public void setUp() {
        initMocks(this);
        fileUploadController = new FileUploadController(
                stubbedFileUploadService, mockedPlayerService, mockedCoachService, mockedTeamService);
    }

    @Test
    public void shouldPassNewPlayersToModelAfterPlayerFileIsUploaded() {
        Set<Player> expectedPlayers = new HashSet<Player>();
        expectedPlayers.add(new PlayerBuilder().withName("Bob").build());
        when(stubbedFileUploadService.createPlayersFrom(mockedFile))
                .thenReturn(expectedPlayers);

        ModelAndView modelAndView = fileUploadController.handlePlayerUpload(mockedFile, "player");
        Set<Player> actualPlayers = (Set<Player>) modelAndView.getModelMap().get("players");

        assertThat(actualPlayers, is(expectedPlayers));
    }

    @Test
    public void shouldPassNewCoachesToModelAfterCoachFileIsUploaded() {
        Set<Coach> expectedCoaches = new HashSet<Coach>();
        expectedCoaches.add(new CoachBuilder().withName("Jack").build());
        when(stubbedFileUploadService.createCoachesFrom(mockedFile))
                .thenReturn(expectedCoaches);

        ModelAndView modelAndView = fileUploadController.handleCoachUpload(mockedFile, "coaches");
        Set<Coach> actualCoaches = (Set<Coach>) modelAndView.getModelMap().get("coaches");

        assertThat(actualCoaches, is(expectedCoaches));
    }

    @Test
    public void shouldUpdatePlayerServiceAfterPlayerFileIsUploaded() {
        Set<Player> players = new HashSet<Player>();
        players.add(new PlayerBuilder().withName("Bob").build());
        when(stubbedFileUploadService.createPlayersFrom(mockedFile))
                .thenReturn(players);

        fileUploadController.handlePlayerUpload(mockedFile, "player");

        verify(mockedPlayerService).setPlayers(players);
    }

    @Test
    public void shouldUpdateCoachServiceAfterCoachFileIsUploaded() {
        Set<Coach> coaches = new HashSet<Coach>();
        coaches.add(new CoachBuilder().withName("Bob").build());
        when(stubbedFileUploadService.createCoachesFrom(mockedFile))
                .thenReturn(coaches);

        fileUploadController.handleCoachUpload(mockedFile, "coach");

        verify(mockedCoachService).setCoaches(coaches);
    }

    @Test
    public void shouldRedirectToHomeIfGoodPlayerFileIsUploaded() {
        when(stubbedFileUploadService.createPlayersFrom(any(MultipartFile.class)))
                .thenReturn(new HashSet<Player>());

        ModelAndView modelAndView = fileUploadController.handlePlayerUpload(mockedFile, "player");
        String redirectPath = modelAndView.getViewName();

        assertEquals("redirect:/", redirectPath);
    }

    @Test
    public void shouldRedirectToHomeIfGoodCoachFileIsUploaded() {
        when(stubbedFileUploadService.createCoachesFrom(any(MultipartFile.class)))
                .thenReturn(new HashSet<Coach>());

        ModelAndView modelAndView = fileUploadController.handleCoachUpload(mockedFile, "coach");
        String redirectPath = modelAndView.getViewName();

        assertEquals("redirect:/", redirectPath);
    }

    @Test
    public void shouldRedirectToErrorPageIfBadPlayerFileIsUploaded() {
        when(stubbedFileUploadService.createPlayersFrom(any(MultipartFile.class)))
                .thenThrow(new RuntimeException());

        ModelAndView modelAndView = fileUploadController.handlePlayerUpload(mockedFile, "player");
        String redirectPath = modelAndView.getViewName();

        assertEquals("redirect:/error?personType=player", redirectPath);
    }

    @Test
    public void shouldRedirectToErrorPageIfBadCoachFileIsUploaded() {
        when(stubbedFileUploadService.createCoachesFrom(any(MultipartFile.class)))
                .thenThrow(new RuntimeException());

        ModelAndView modelAndView = fileUploadController.handleCoachUpload(mockedFile, "coach");
        String redirectPath = modelAndView.getViewName();

        assertEquals("redirect:/error?personType=coach", redirectPath);
    }

}