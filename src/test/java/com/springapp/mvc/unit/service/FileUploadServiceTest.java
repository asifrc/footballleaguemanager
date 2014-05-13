package com.springapp.mvc.unit.service;

import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.CoachBuilder;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.FileUploadService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FileUploadServiceTest {
    private static final String TEST_ROOT = "./src/test/java/com/springapp/mvc/";
    public static final String PLAYER_FILE_1 = TEST_ROOT + "playerFile1.txt";
    public static final String BAD_PLAYER_FILE_1 = TEST_ROOT + "badPlayerFile1.txt";
    public static final String COACH_FILE_1 = TEST_ROOT + "coachFile.txt";
    public static final String EMPTY_TEXT_FILE = TEST_ROOT + "empty.txt";

    @Mock MultipartFile stubbedFile;
    FileUploadService fileUploadService;

    @Before
    public void setUp() {
        initMocks(this);
        fileUploadService = new FileUploadService();
    }

    @Test
    public void shouldReturnEmptySetWhenEmptyPlayerFileUploaded() throws IOException {
        when(stubbedFile.getInputStream())
                .thenReturn(new FileInputStream(EMPTY_TEXT_FILE));

        Set<Player> actualPlayers = fileUploadService.createPlayersFrom(stubbedFile);

        assertThat(actualPlayers.isEmpty(), is(true));
    }

    @Test
    public void shouldReturnEmptySetWhenEmptyCoachFileUploaded() throws IOException {
        when(stubbedFile.getInputStream())
                .thenReturn(new FileInputStream(EMPTY_TEXT_FILE));

        Set<Coach> actualCoaches = fileUploadService.createCoachesFrom(stubbedFile);

        assertThat(actualCoaches.isEmpty(), is(true));
    }

    @Test
    public void shouldReturnWithBobWhenPlayerFileWithBobUploaded() throws IOException {
        Player bob = new PlayerBuilder().withName("Bob")
                .withTeam("Team2")
                .withNumber("1")
                .withAge(17)
                .build();
        when(stubbedFile.getInputStream())
                .thenReturn(new FileInputStream(PLAYER_FILE_1));

        Set<Player> actualPlayers = fileUploadService.createPlayersFrom(stubbedFile);

        assertTrue(actualPlayers.contains(bob));
    }

    @Test
    public void shouldReturnWithJackWhenCoachFileWithJackUploaded() throws IOException {
        Coach jack = new CoachBuilder().withName("Jack")
                .withTeam("Team1")
                .withPosition("Assistant Coach")
                .build();
        when(stubbedFile.getInputStream())
                .thenReturn(new FileInputStream(COACH_FILE_1));

        Set<Coach> actualCoaches = fileUploadService.createCoachesFrom(stubbedFile);

        assertTrue(actualCoaches.contains(jack));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void shouldThrowRunTimeExceptionWhenAgeIsNotAnInt() throws IOException {
        thrown.expect(RuntimeException.class);
        when(stubbedFile.getInputStream())
                .thenReturn(new FileInputStream(BAD_PLAYER_FILE_1));

        fileUploadService.createPlayersFrom(stubbedFile);
    }
}