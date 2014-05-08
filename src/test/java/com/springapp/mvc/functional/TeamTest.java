package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class TeamTest extends FunctionalBase {
    @Test
    public void shouldDisplayTeamNameInHeaderOnTeamPage() throws Exception {
        driver.get(BASE_URL + "team/?name=Rockets");

        WebElement teamElement = driver.findElement(By.id("team-name"));

        assertEquals("Rockets", teamElement.getText());
    }
}
