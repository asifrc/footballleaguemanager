package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.springapp.mvc.functional.FileUploadHelper.PLAYER_FILE_2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RecordGameTest extends FunctionalBase {

    @Test
    public void shouldDisplayResultsOfGameAfterSubmittingGameForm() {
        driver.get(BASE_URL);
        helper.uploadFileFor("players", PLAYER_FILE_2);

        goToRecordGamesPageByClickingOnTheMenuLink();

        pageShouldHaveProperTitle();

        dropdownsShouldHaveAllTeams();

        userSubmitsFormWithScores();

        pageShouldDisplaySuccessMessage();

    }

    private void pageShouldHaveProperTitle() {
        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertEquals("Record Game Results", h1.getText());
    }

    private void goToRecordGamesPageByClickingOnTheMenuLink() {
        List<WebElement> menuItems = driver.findElements(By.className("menuItem"));
        WebElement link = menuItems.get(3);

        assertEquals("Record Game Results", link.getText());

        link.click();
    }

    private void dropdownsShouldHaveAllTeams() {
        Select team1 = new Select(driver.findElement(By.id("team1-dropdown")));
        List<WebElement> teamElements = team1.getOptions();

        List<String> actualTeams = new ArrayList<String>();

        for (WebElement teamElement : teamElements) {
            actualTeams.add(teamElement.getText());
        }

        List<String> expectedTeams = Arrays.asList("-- Choose Team --", "Dallas Cowboys", "Canadian Argos", "Da Behrs",
                "Barcelona", "Rockets");
        assertTrue(actualTeams.containsAll(expectedTeams));
        assertEquals(expectedTeams.size(), teamElements.size());
    }

    private void userSubmitsFormWithScores() {
        Select team1 = new Select(driver.findElement(By.id("team1-dropdown")));
        team1.selectByVisibleText("Dallas Cowboys");
        WebElement team1Score = driver.findElement(By.id("team1-score"));
        team1Score.sendKeys("10");

        Select team2 = new Select(driver.findElement(By.id("team2-dropdown")));
        team2.selectByVisibleText("Canadian Argos");
        WebElement team2Score = driver.findElement(By.id("team2-score"));
        team2Score.sendKeys("9");

        WebElement submit = driver.findElement(By.id("record-game-form")).findElement(By.id("submit"));
        submit.click();
    }

    private void pageShouldDisplaySuccessMessage() {
        WebElement flash = driver.findElement(By.id("flash"));
        assertEquals("Dallas Cowboys: W, Canadian Argos: L", flash.getText());
    }
}
