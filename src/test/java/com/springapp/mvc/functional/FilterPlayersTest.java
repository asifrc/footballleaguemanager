package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.springapp.mvc.functional.FileUploadHelper.PLAYER_LIST_1;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FilterPlayersTest extends FunctionalBase {
    // correct way to select a dropdown in selenium.
    // http://docs.seleniumhq.org/docs/03_webdriver.jsp#user-input-filling-in-forms

    @Test
    public void filterPlayersButtonShouldBeDisabledUntilANumberIsChosen() throws Exception {
        driver.get(BASE_URL);
        helper.uploadFileFor("players", PLAYER_LIST_1);
        WebElement filterButton = driver.findElement(By.id("player-filter-button"));

        assertThat(filterButton.getAttribute("disabled"), is("true"));
    }

    @Test
    public void shouldFilterPlayersOver19YearsOldWhen19YearFilterSelectedAndFilterButtonClicked() {
        driver.get(BASE_URL);
        helper.uploadFileFor("players", PLAYER_LIST_1);

        WebElement playerList = driver.findElement(By.id("player-table"));

        assertTrue((playerList.findElement(By.id("player-2")).getText()
                .contains("Bob")));

        Select filterOptions = new Select(driver.findElement(By.id("age-filter-dropdown")));
        filterOptions.selectByVisibleText("19");

        WebElement filterButton = driver.findElement(By.id("player-filter-button"));
        filterButton.click();

        playerList = driver.findElement(By.id("player-table"));

        assertFalse(playerList.getText()
                .contains("Bob"));
    }
}
