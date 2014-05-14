package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.springapp.mvc.functional.FileUploadHelper.COACH_FILE;
import static com.springapp.mvc.functional.FileUploadHelper.PLAYER_FILE_2;
import static org.junit.Assert.*;

public class TeamTest extends FunctionalBase {
    @Test
    public void shouldDisplayTeamNameInHeaderOnTeamPage() {
        driver.get(BASE_URL + "team/?name=Rockets");

        WebElement teamNameHeader = driver.findElement(By.id("team-name"));

        assertEquals("Rockets", teamNameHeader.getText());
    }

    @Test
    public void shouldDisplayPlayerAndCoachTablesOnPage() {
        driver.get(BASE_URL);
        helper.uploadFileFor("players", PLAYER_FILE_2);
        helper.uploadFileFor("coaches", COACH_FILE);
        driver.get(BASE_URL + "team/?name=Rockets");

        WebElement playerDiv = driver.findElement(By.id("players"));
        WebElement coachDiv = driver.findElement(By.id("coaches"));

        assertTrue(playerDiv.getText().contains("Aubrey"));
        assertTrue(coachDiv.getText().contains("Hakim"));
    }


    @Test
    public void shouldNotDisplayPlayersNotOnTheTeam() {
        driver.get(BASE_URL);
        helper.uploadFileFor("players", PLAYER_FILE_2);
        helper.uploadFileFor("coaches", COACH_FILE);
        driver.get(BASE_URL + "team/?name=Rockets");

        WebElement playerDiv = driver.findElement(By.id("players"));
        WebElement coachDiv = driver.findElement(By.id("coaches"));

        assertTrue(playerDiv.getText().contains("Aubrey"));
        assertFalse(playerDiv.getText().contains("Whitney"));

        assertTrue(coachDiv.getText().contains("Hakim"));
        assertFalse(coachDiv.getText().contains("Jack"));
    }

}
