package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.springapp.mvc.functional.FileUploadHelper.COACH_LIST;
import static com.springapp.mvc.functional.FileUploadHelper.PLAYER_LIST_2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TeamTest extends FunctionalBase {
    @Test
    public void shouldDisplayTeamNameInHeaderOnTeamPage() throws Exception {
        driver.get(BASE_URL + "team/?name=Rockets");

        WebElement teamNameHeader = driver.findElement(By.id("team-name"));

        assertEquals("Rockets", teamNameHeader.getText());
    }

    @Test
    public void shouldDisplayPlayerListAndCoachListOnPage() throws Exception {
        driver.get(BASE_URL);
        helper.uploadFileFor("players", PLAYER_LIST_2);
        helper.uploadFileFor("coaches", COACH_LIST);
        driver.get(BASE_URL + "team/?name=Rockets");

        WebElement playerDiv = driver.findElement(By.id("players"));
        WebElement coachDiv = driver.findElement(By.id("coaches"));

        assertTrue(playerDiv.getText().contains("Aubrey"));
        assertTrue(coachDiv.getText().contains("Jack"));
    }
}
