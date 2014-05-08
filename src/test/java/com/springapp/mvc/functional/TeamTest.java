package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
    public void shouldDisplayPlayerListOnPage() throws Exception {
        driver.get(BASE_URL);
        helper.uploadFileFor("players", PLAYER_LIST_2);
        driver.get(BASE_URL + "team/?name=Rockets");

        WebElement playerTable = driver.findElement(By.id("players"));

        assertTrue(playerTable.getText().contains("Aubrey"));
    }
}
