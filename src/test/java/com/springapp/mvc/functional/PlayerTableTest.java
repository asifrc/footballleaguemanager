package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.springapp.mvc.functional.FileUploadHelper.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PlayerTableTest extends FunctionalBase {

    @Test
    public void uploadButtonsShouldBeDisabledWhenNoFilesAreSelectedToUpload() {
        driver.get(BASE_URL);

        List<WebElement> uploadButtons = driver.findElements(By.className("upload-button"));

        for (WebElement uploadButton : uploadButtons) {
            assertThat(uploadButton.getAttribute("disabled"), is("true"));
        }
    }

    @Test
    public void shouldDisplaySorryMessageWhenNoPlayersInTable() {
        driver.get(BASE_URL);

        helper.uploadFileFor("players", EMPTY_TEXT_FILE);
        WebElement emptyMessage = driver.findElement(By.id("empty-list-message"));

        assertEquals("Sorry, there are no players.", emptyMessage.getText());
    }

    @Test
    public void shouldListPlayerNames() {
        driver.get(BASE_URL);

        helper.uploadFileFor("players", PLAYER_FILE_1);

        WebElement playerTable = driver.findElement(By.id("player-table"));

        assertTrue(playerTable.getText().contains("Sally"));
        assertTrue(playerTable.getText().contains("Bob"));
    }

    @Test
    public void shouldReplacePlayersAfterSecondUpload() {
        driver.get(BASE_URL);

        helper.uploadFileFor("players", PLAYER_FILE_1);

        WebElement playerTable = driver.findElement(By.id("player-table"));

        assertTrue((playerTable.findElement(By.id("player-1")).getText()
                .contains("Sally")));

        assertTrue((playerTable.findElement(By.id("player-2")).getText()
                .contains("Bob")));

        helper.uploadFileFor("players", PLAYER_FILE_2);

        playerTable = driver.findElement(By.id("player-table"));

        assertTrue(playerTable.getText().contains("Cameron"));
        assertFalse(playerTable.getText().contains("Sally"));
    }


}
