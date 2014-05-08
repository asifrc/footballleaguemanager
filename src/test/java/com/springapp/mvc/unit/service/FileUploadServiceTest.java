package com.springapp.mvc.unit.service;

import com.springapp.mvc.model.*;
import com.springapp.mvc.service.FileUploadService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileUploadServiceTest {
    private static final String TEST_ROOT = "./src/test/java/com/springapp/mvc/";
    public static final String PLAYER_LIST_1 = TEST_ROOT + "playerList1.txt";
    public static final String BAD_PLAYER_LIST_1 = TEST_ROOT + "badPlayerList1.txt";
    public static final String COACH_LIST_1 = TEST_ROOT + "coachList1.txt";
    public static final String EMPTY_TEXT_FILE = TEST_ROOT + "empty.txt";

    @Mock MultipartFile stubbedFile;
    FileUploadService fileUploadService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        fileUploadService = new FileUploadService();
    }

    @Test
    public void shouldReturnEmptyListWhenEmptyPlayerFileUploaded() throws IOException {
        when(stubbedFile.getInputStream())
                .thenReturn(new FileInputStream(EMPTY_TEXT_FILE));

        List<Player> actualPlayerList = fileUploadService.createPlayerList(stubbedFile);

        assertThat(actualPlayerList.isEmpty(), is(true));
    }

    @Test
    public void shouldReturnEmptyListWhenEmptyCoachFileUploaded() throws IOException {
        when(stubbedFile.getInputStream())
                .thenReturn(new FileInputStream(EMPTY_TEXT_FILE));

        List<Coach> actualCoachList = fileUploadService.createCoachList(stubbedFile);

        assertThat(actualCoachList.isEmpty(), is(true));
    }

    @Test
    public void shouldReturnListWithBobWhenPlayerFileWithBobUploaded() throws Exception {
        Player bob = new PlayerBuilder().withName("Bob")
                .withTeam("Team2")
                .withNumber("1")
                .withAge(17)
                .build();
        when(stubbedFile.getInputStream())
                .thenReturn(new FileInputStream(PLAYER_LIST_1));

        List<Player> actualPlayerList = fileUploadService.createPlayerList(stubbedFile);

        assertTrue(actualPlayerList.contains(bob));
    }

    @Test
    public void shouldReturnListWithJackWhenCoachFileWithJackUploaded() throws Exception {
        Coach jack = new CoachBuilder().withName("Jack")
                .withTeam("Team1")
                .withPosition("Assistant Coach")
                .build();
        when(stubbedFile.getInputStream())
                .thenReturn(new FileInputStream(COACH_LIST_1));

        List<Coach> actualCoachList = fileUploadService.createCoachList(stubbedFile);

        assertTrue(actualCoachList.contains(jack));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void shouldThrowRunTimeExceptionWhenAgeIsNotAnInt() throws IOException {
        thrown.expect(RuntimeException.class);
        when(stubbedFile.getInputStream())
                .thenReturn(new FileInputStream(BAD_PLAYER_LIST_1));

        fileUploadService.createPlayerList(stubbedFile);
    }
}