package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;

import static org.junit.Assert.*;

public class PlayerListTest {
    private static final String BASE_URL = "http://localhost:8080/";
    private WebDriver driver = new HtmlUnitDriver();
    private static final String PLAYER_LIST_EMPTY =
            "./src/test/java/com/springapp/mvc/playerListEmpty.txt";
    public static final String PLAYER_LIST_1 =
            "./src/test/java/com/springapp/mvc/playerList1.txt";
    private static final String PLAYER_LIST_2 =
            "./src/test/java/com/springapp/mvc/playerList2.txt";

    @Test
    public void shouldDisplaySorryMessageForEmptyPlayerList() {
        driver.get(BASE_URL);
        uploadFile(PLAYER_LIST_EMPTY);
        WebElement emptyMessage = driver.findElement(By.id("empty-list-message"));
        assertEquals("Sorry, there are no players.", emptyMessage.getText());
    }

    @Test
    public void shouldListPlayerNames() {
        driver.get(BASE_URL);

        uploadFile(PLAYER_LIST_1);

        WebElement playerList = driver.findElement(By.id("player-list"));

        assertTrue(playerList.getText().contains("Sally"));
        assertTrue(playerList.getText().contains("Bob"));
    }

    @Test
    public void shouldReplacePlayerListAfterSecondUpload() {
        driver.get(BASE_URL);

        uploadFile(PLAYER_LIST_1);

        WebElement playerList = driver.findElement(By.id("player-list"));

        assertEquals("Sally", playerList.findElement(By.id("player-1")).getText());
        assertEquals("Bob", playerList.findElement(By.id("player-2")).getText());

        uploadFile(PLAYER_LIST_2);

        playerList = driver.findElement(By.id("player-list"));

        assertTrue(playerList.getText().contains("Cameron"));
        assertFalse(playerList.getText().contains("Sally"));
    }

    private void uploadFile(String relativeFilePath) {
        WebElement uploadField = driver.findElement(By.id("upload-field"));
        uploadField.sendKeys(getAbsolutePath(relativeFilePath));

        WebElement uploadButton = driver.findElement(By.id("upload-button"));
        uploadButton.click();
    }

    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        return file.getAbsolutePath();
    }
}
