package com.springapp.mvc.unit.service;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.FileUploadService;
import org.junit.Before;
import org.junit.Test;
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
    public static final String PLAYER_LIST_2 = TEST_ROOT + "playerList2.txt";
    public static final String PLAYER_LIST_EMPTY = TEST_ROOT + "playerListEmpty.txt";

    @Mock
    MultipartFile stubbedFile;
    FileUploadService fileUploadService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        fileUploadService = new FileUploadService();
    }

    @Test
    public void shouldReturnEmptyListWhenEmptyFileUploaded() throws IOException {
        when(stubbedFile.getInputStream())
                .thenReturn(new FileInputStream(PLAYER_LIST_EMPTY));

        List<Player> actualPlayerList = fileUploadService.createPlayerList(stubbedFile);

        assertThat(actualPlayerList.isEmpty(), is(true));
    }

    @Test
    public void shouldReturnListWithBobWhenFileWithBobUploaded() throws Exception {
        Player bob = new PlayerBuilder().withName("Bob")
                                        .withTeam("Team2")
                                        .withNumber("1")
                                        .withAge(22)
                                        .build();
        when(stubbedFile.getInputStream())
                .thenReturn(new FileInputStream(PLAYER_LIST_1));

        List<Player> actualPlayerList = fileUploadService.createPlayerList(stubbedFile);

        assertTrue(isInList(actualPlayerList, bob));
    }

    private Boolean isInList(List<Player> playerList, Player expectedPlayer) {
        for (Player player : playerList) {
            if (player.equals(expectedPlayer)) {
                return true;
            }
        }
        return false;
    }
}