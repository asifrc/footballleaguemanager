package com.springapp.mvc.functional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.*;
import static com.springapp.mvc.functional.FileUploadHelper.*;

public class PlayerListTest {
    private static final String BASE_URL = "http://localhost:8080/";
    private WebDriver driver;
    private FileUploadHelper helper;

    @Before
    public void setUp() throws Exception {
        driver = new HtmlUnitDriver();
        helper = new FileUploadHelper(driver);
    }

    @After
    public void tearDown() throws Exception {
        driver.get(BASE_URL);
        helper.uploadFile(PLAYER_LIST_EMPTY);
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

        WebElement playerList = driver.findElement(By.id("player-list"));

        assertTrue(playerList.getText().contains("Sally"));
        assertTrue(playerList.getText().contains("Bob"));
    }

    @Test
    public void shouldReplacePlayerListAfterSecondUpload() {
        driver.get(BASE_URL);

        helper.uploadFile(PLAYER_LIST_1);

        WebElement playerList = driver.findElement(By.id("player-list"));

        assertEquals("Sally", playerList.findElement(By.id("player-1")).getText());
        assertEquals("Bob", playerList.findElement(By.id("player-2")).getText());

        helper.uploadFile(PLAYER_LIST_2);

        playerList = driver.findElement(By.id("player-list"));

        assertTrue(playerList.getText().contains("Cameron"));
        assertFalse(playerList.getText().contains("Sally"));
    }
}
