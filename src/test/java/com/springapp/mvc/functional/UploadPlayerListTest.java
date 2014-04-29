package com.springapp.mvc.functional;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;

public class UploadPlayerListTest {
    public static final String BASE_URL = "http://localhost:8080/";
    public static final String PLAYER_LIST_FILE =
            "/Users/crowshan/beach/footballLeagueManager/footballleaguemanager/src/test/java/com/springapp/mvc/playerList.txt";
    WebDriver driver = new HtmlUnitDriver();

    @Ignore("George and Cameron: TDD'ing the upload functionality... Handle the post request")
    @Test
    public void shouldHaveUploadPlayerListTextFileButton() throws Exception {
        driver.get(BASE_URL);

        WebElement uploadField = driver.findElement(By.id("upload-field"));
        uploadField.sendKeys(PLAYER_LIST_FILE);

        WebElement uploadButton = driver.findElement(By.id("upload-button"));
        uploadButton.click();

        WebElement player_list = driver.findElement(By.id("player-list"));

        assertEquals("Sally", player_list.findElement(By.id("player-1")).getText());
        assertEquals("Bob", player_list.findElement(By.id("player-2")).getText());
    }
}
