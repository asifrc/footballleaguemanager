package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.springapp.mvc.functional.FileUploadHelper.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PlayerListTest extends FunctionalBase {

    @Test
    public void uploadButtonShouldBeDisabledWhenNoFileIsSelectedToUpload() {
        driver.get(BASE_URL);

        WebElement uploadButton = driver.findElement(By.id("upload-button"));

        assertThat(uploadButton.getAttribute("disabled"), is("true"));
    }

    @Test
    public void shouldDisplaySorryMessageForEmptyPlayerList() {
        driver.get(BASE_URL);

        helper.uploadFile(PLAYER_LIST_EMPTY);
        WebElement emptyMessage = driver.findElement(By.id("empty-list-message"));

        assertEquals("Sorry, there are no players.", emptyMessage.getText());
    }

    @Test
    public void shouldListPlayerNames() {
        driver.get(BASE_URL);

        helper.uploadFile(PLAYER_LIST_1);

        WebElement playerList = driver.findElement(By.id("player-table"));

        assertTrue(playerList.getText().contains("Sally"));
        assertTrue(playerList.getText().contains("Bob"));
    }

    @Test
    public void shouldReplacePlayerListAfterSecondUpload() {
        driver.get(BASE_URL);

        helper.uploadFile(PLAYER_LIST_1);

        WebElement playerList = driver.findElement(By.id("player-table"));

        assertTrue((playerList.findElement(By.id("player-1")).getText()
                .contains("Sally")));

        assertTrue((playerList.findElement(By.id("player-2")).getText()
                .contains("Bob")));

        helper.uploadFile(PLAYER_LIST_2);

        playerList = driver.findElement(By.id("player-table"));

        assertTrue(playerList.getText().contains("Cameron"));
        assertFalse(playerList.getText().contains("Sally"));
    }
}
