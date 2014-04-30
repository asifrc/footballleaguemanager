package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class PlayerListTest {
    private static final String BASE_URL = "http://localhost:8080/";
    private WebDriver driver = new HtmlUnitDriver();
    public static final String PLAYER_LIST_FILE =
            "./src/test/java/com/springapp/mvc/playerList.txt";

    @Test
    public void shouldListTwoPlayerNames() {
        driver.get(BASE_URL);

        WebElement uploadField = driver.findElement(By.id("upload-field"));
        uploadField.sendKeys(getAbsolutePath(PLAYER_LIST_FILE));

        WebElement uploadButton = driver.findElement(By.id("upload-button"));
        uploadButton.click();

        WebElement player_list = driver.findElement(By.id("player-list"));

        assertEquals("Sally", player_list.findElement(By.id("player-1")).getText());
        assertEquals("Bob", player_list.findElement(By.id("player-2")).getText());
    }

    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        return file.getAbsolutePath();
    }
}
