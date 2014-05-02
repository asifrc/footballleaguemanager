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

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
        MultipartFile file = mock(MultipartFile.class);

        List<Player> expectedPlayerList = new ArrayList<Player>();
        expectedPlayerList.add(new PlayerBuilder().withName("Bob").build());
        when(stubbedFileUploadService.createPlayerList(file)).thenReturn(expectedPlayerList);


        ModelAndView modelAndView = fileUploadController.uploadFile(file);

        List<Player> actualPlayerList = (List<Player>) modelAndView.getModelMap().get("playerList");
        assertThat(actualPlayerList, is(expectedPlayerList));
    }

    @Test
    public void shouldUpdatePlayerServiceAfterPlayerListFileIsUploaded() {
        MultipartFile file = mock(MultipartFile.class);

        List<Player> playerList = new ArrayList<Player>();
        playerList.add(new PlayerBuilder().withName("Bob").build());
        when(stubbedFileUploadService.createPlayerList(file)).thenReturn(playerList);

        ModelAndView modelAndView = fileUploadController.handleUpload(file);

        verify(mockedPlayerService).setPlayerList(playerList);
    }
}
    @Test
    public void shouldRedirectToHomeIfGoodFileIsUploaded() throws Exception {
        when(stubbedFileUploadService
                .createPlayerList(any(MultipartFile.class)))
                .thenReturn(new ArrayList<Player>());

        String actual = fileUploadController.handleUpload(mockedFile);
        assertEquals("redirect:/", actual);
    }

    @Test
    public void shouldRedirectToErrorPageIfBadFileIsUploaded() throws Exception {

    }