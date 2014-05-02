package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.FileUploadController;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.FileUploadService;
import com.springapp.mvc.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileUploadControllerTest {

    @Mock PlayerService mockedPlayerService;
    @Mock MultipartFile mockedFile;
    @Mock FileUploadService stubbedFileUploadService;

    private FileUploadController fileUploadController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        fileUploadController = new FileUploadController(stubbedFileUploadService, mockedPlayerService);
    }

    @Test
    public void shouldPassNewPlayerListToModelAfterPlayerListFileIsUploaded() {
        List<Player> expectedPlayerList = new ArrayList<Player>();
        expectedPlayerList.add(new PlayerBuilder().withName("Bob").build());
        when(stubbedFileUploadService.createPlayerList(mockedFile)).thenReturn(expectedPlayerList);

        ModelAndView modelAndView = fileUploadController.uploadFile(mockedFile);

        List<Player> actualPlayerList = (List<Player>) modelAndView.getModelMap().get("playerList");
        assertThat(actualPlayerList, is(expectedPlayerList));
    }

    @Test
    public void shouldUpdatePlayerServiceAfterPlayerListFileIsUploaded() {
        List<Player> playerList = new ArrayList<Player>();
        playerList.add(new PlayerBuilder().withName("Bob").build());
        when(stubbedFileUploadService.createPlayerList(mockedFile)).thenReturn(playerList);

        fileUploadController.uploadFile(mockedFile);

        verify(mockedPlayerService).setPlayerList(playerList);
    }

    @Test
    public void shouldRedirectToHomeIfGoodFileIsUploaded() throws Exception {
        when(stubbedFileUploadService
                .createPlayerList(any(MultipartFile.class)))
                .thenReturn(new ArrayList<Player>());

        ModelAndView modelAndView = fileUploadController.uploadFile(mockedFile);
        String actual = modelAndView.getViewName();
        assertEquals("redirect:/", actual);
    }

    @Test
    public void shouldRedirectToErrorPageIfBadFileIsUploaded() throws Exception {
        when(stubbedFileUploadService
                .createPlayerList(any(MultipartFile.class)))
                .thenThrow(new ArrayIndexOutOfBoundsException());

        ModelAndView modelAndView = fileUploadController.uploadFile(mockedFile);
        String actual = modelAndView.getViewName();

        assertEquals("redirect:/error", actual);
    }
}