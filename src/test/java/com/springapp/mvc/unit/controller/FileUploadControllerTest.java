package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.FileUploadController;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.FileUploadService;
import com.springapp.mvc.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileUploadControllerTest {

    @Mock FileUploadService mockedFileUploadService;
    @Mock PlayerService mockedPlayerService;
    @Mock MultipartFile mockedFile;

    FileUploadController fileUploadController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        fileUploadController = new FileUploadController(mockedFileUploadService, mockedPlayerService);
    }

    @Test
    public void shouldRedirectToHomeIfGoodFileIsUploaded() throws Exception {
        when(mockedFileUploadService
                .createPlayerList(any(MultipartFile.class)))
                .thenReturn(new ArrayList<Player>());

        String actual = fileUploadController.handleUpload(mockedFile);
        assertEquals("redirect:/", actual);
    }

    @Test
    public void shouldRedirectToErrorPageIfBadFileIsUploaded() throws Exception {

    }

}
