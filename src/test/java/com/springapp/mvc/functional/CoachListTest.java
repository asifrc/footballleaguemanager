package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class CoachListTest extends FunctionalBase {

    @Test
    public void shouldShowCoachesWhenCoachListIsNotEmpty() {
        driver.get(BASE_URL);

        WebElement coachList = driver.findElement(By.id("coach-table"));

        assertTrue(coachList.getText().contains("Jack"));
        assertTrue(coachList.getText().contains("Jill"));

    }
}
