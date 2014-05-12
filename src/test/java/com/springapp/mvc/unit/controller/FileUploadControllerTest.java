package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.FileUploadController;
import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.CoachBuilder;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.FileUploadService;
import com.springapp.mvc.service.PlayerService;
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

    private FileUploadController fileUploadController;

    @Before
    public void setUp() {
        initMocks(this);
        fileUploadController = new FileUploadController(
                stubbedFileUploadService, mockedPlayerService, mockedCoachService);
    }

    @Test
    public void shouldPassNewPlayerListToModelAfterPlayerListFileIsUploaded() {
        Set<Player> expectedPlayerList = new HashSet<Player>();
        expectedPlayerList.add(new PlayerBuilder().withName("Bob").build());
        when(stubbedFileUploadService.createPlayerList(mockedFile))
                .thenReturn(expectedPlayerList);

        ModelAndView modelAndView = fileUploadController.handlePlayerUpload(mockedFile, "player");
        Set<Player> actualPlayerList = (Set<Player>) modelAndView.getModelMap().get("playerList");

        assertThat(actualPlayerList, is(expectedPlayerList));
    }

    @Test
    public void shouldPassNewCoachListToModelAfterCoachListFileIsUploaded() {
        Set<Coach> expectedCoachList = new HashSet<Coach>();
        expectedCoachList.add(new CoachBuilder().withName("Jack").build());
        when(stubbedFileUploadService.createCoachList(mockedFile))
                .thenReturn(expectedCoachList);

        ModelAndView modelAndView = fileUploadController.handleCoachUpload(mockedFile, "coach");
        Set<Coach> actualCoachList = (Set<Coach>) modelAndView.getModelMap().get("coachList");

        assertThat(actualCoachList, is(expectedCoachList));
    }

    @Test
    public void shouldUpdatePlayerServiceAfterPlayerListFileIsUploaded() {
        Set<Player> playerList = new HashSet<Player>();
        playerList.add(new PlayerBuilder().withName("Bob").build());
        when(stubbedFileUploadService.createPlayerList(mockedFile))
                .thenReturn(playerList);

        fileUploadController.handlePlayerUpload(mockedFile, "player");

        verify(mockedPlayerService).setPlayerList(playerList);
    }

    @Test
    public void shouldUpdateCoachServiceAfterCoachListFileIsUploaded() {
        Set<Coach> coachList = new HashSet<Coach>();
        coachList.add(new CoachBuilder().withName("Bob").build());
        when(stubbedFileUploadService.createCoachList(mockedFile))
                .thenReturn(coachList);

        fileUploadController.handleCoachUpload(mockedFile, "coach");

        verify(mockedCoachService).setCoachList(coachList);
    }

    @Test
    public void shouldRedirectToHomeIfGoodPlayerListIsUploaded() {
        when(stubbedFileUploadService.createPlayerList(any(MultipartFile.class)))
                .thenReturn(new HashSet<Player>());

        ModelAndView modelAndView = fileUploadController.handlePlayerUpload(mockedFile, "player");
        String redirectPath = modelAndView.getViewName();

        assertEquals("redirect:/", redirectPath);
    }

    @Test
    public void shouldRedirectToHomeIfGoodCoachListIsUploaded() {
        when(stubbedFileUploadService.createCoachList(any(MultipartFile.class)))
                .thenReturn(new HashSet<Coach>());

        ModelAndView modelAndView = fileUploadController.handleCoachUpload(mockedFile, "coach");
        String redirectPath = modelAndView.getViewName();

        assertEquals("redirect:/", redirectPath);
    }

    @Test
    public void shouldRedirectToErrorPageIfBadPlayerListIsUploaded() {
        when(stubbedFileUploadService.createPlayerList(any(MultipartFile.class)))
                .thenThrow(new RuntimeException());

        ModelAndView modelAndView = fileUploadController.handlePlayerUpload(mockedFile, "player");
        String redirectPath = modelAndView.getViewName();

        assertEquals("redirect:/error?personType=player", redirectPath);
    }

    @Test
    public void shouldRedirectToErrorPageIfBadCoachListIsUploaded() {
        when(stubbedFileUploadService.createCoachList(any(MultipartFile.class)))
                .thenThrow(new RuntimeException());

        ModelAndView modelAndView = fileUploadController.handleCoachUpload(mockedFile, "coach");
        String redirectPath = modelAndView.getViewName();

        assertEquals("redirect:/error?personType=coach", redirectPath);
    }

}