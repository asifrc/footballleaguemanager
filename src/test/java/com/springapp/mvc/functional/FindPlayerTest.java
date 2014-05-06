package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.springapp.mvc.functional.FileUploadHelper.PLAYER_LIST_1;
import static org.junit.Assert.assertEquals;

public class FindPlayerTest extends FunctionalBase {

    @Test
    public void shouldFindAPlayerWhenMatchingNameAndNumberIsSearchedFor() throws Exception {
        driver.get(BASE_URL);
        helper.uploadFileFor("players", PLAYER_LIST_1);

        driver.get(BASE_URL + "find");

        WebElement nameTextBox = driver.findElement(By.id("name"));
        WebElement numberTextBox = driver.findElement(By.id("number"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        nameTextBox.sendKeys("Sally");
        numberTextBox.sendKeys("0");
        submitButton.click();

        WebElement player = driver.findElement(By.id("playerText"));
        assertEquals("#0 Sally", player.getText());
    }

    @Test
    public void shouldDisplayMessageWhenSearchReturnsNoMatchingPlayers() throws Exception {
        driver.get(BASE_URL);
        helper.uploadFileFor("players", PLAYER_LIST_1);

        driver.get(BASE_URL + "find");

        WebElement nameTextBox = driver.findElement(By.id("name"));
        WebElement numberTextBox = driver.findElement(By.id("number"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        nameTextBox.sendKeys("N0TARealUser");
        numberTextBox.sendKeys("NaN0");
        submitButton.click();

        WebElement error = driver.findElement(By.id("error"));
        assertEquals("Sorry, there is no player with that name and number", error.getText());
    }

    @Test
    public void shouldDisplayMessageWhenSearchMatchesNameButNotNumber() throws Exception {
        driver.get(BASE_URL);
        helper.uploadFileFor("players", PLAYER_LIST_1);

        driver.get(BASE_URL + "find");

        WebElement nameTextBox = driver.findElement(By.id("name"));
        WebElement numberTextBox = driver.findElement(By.id("number"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        nameTextBox.sendKeys("Bob");
        numberTextBox.sendKeys("9000");
        submitButton.click();

        WebElement error = driver.findElement(By.id("error"));
        assertEquals("Sorry, that name and number do not match. Please try again.", error.getText());
    }
}
