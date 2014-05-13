package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;
import static com.springapp.mvc.functional.FileUploadHelper.*;

public class CoachTableTest extends FunctionalBase {

    @Test
    public void shouldShowCoachesWhenCoachFileIsNotEmpty() {
        driver.get(BASE_URL);

        helper.uploadFileFor("coaches", COACH_FILE);
        WebElement coachTable = driver.findElement(By.id("coach-table"));

        assertTrue(coachTable.getText().contains("Jack"));
        assertTrue(coachTable.getText().contains("Jill"));

    }
}
