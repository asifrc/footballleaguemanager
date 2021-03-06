package com.springapp.mvc.functional;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.springapp.mvc.functional.FileUploadHelper.PLAYER_FILE_1;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TradeTest extends FunctionalBase {
    @Before
    public void setUp() throws Exception {
        driver.get(BASE_URL);
        helper.uploadFileFor("players", PLAYER_FILE_1);
    }

    @Test
    public void shouldBeAbleToGoToTradePlayersPageFromHomePage() {
        findTradePlayersLinkAndClickIt();

        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertEquals("Trade Players", h1.getText());
        assertTrue(driver.findElement(By.id("player-table")).getText().contains("Sally"));
    }

    @Test
    public void shouldShowADropdownListWithCorrectTeamsNextToEachPlayerOnTradePage() throws Exception {
        findTradePlayersLinkAndClickIt();

        List<String> teams = Arrays.asList("Team1", "Team2", "-- Trade --");

        List<WebElement> dropDowns = driver.findElements(By.className("team-dropdown"));

        int i=0;
        for (WebElement dropDown : dropDowns) {
            List<String> expectedDropDownOptions = new LinkedList(teams);
            expectedDropDownOptions.remove(i);

            List<String> actualDropDownOptions = getDropDownOptionsFrom(dropDown);

            assertEquals(expectedDropDownOptions.size(), actualDropDownOptions.size());
            assertTrue(actualDropDownOptions.containsAll(expectedDropDownOptions));
            i++;
        }
    }

    @Test
    public void shouldAllowUserToTradeAPlayer() throws Exception {
        WebElement sallyRow;

        sallyRow = driver.findElement(By.id("player-1"));
        assertTrue(sallyRow.getText().contains("Team1"));

        findTradePlayersLinkAndClickIt();

        List<WebElement> dropDowns = driver.findElements(By.className("team-dropdown"));
        Select dropDown = new Select(dropDowns.get(0));
        dropDown.selectByVisibleText("Team2");

        WebElement tradeButton = driver.findElement(By.id("player-trade-button"));
        tradeButton.click();

        assertThat(driver.getCurrentUrl(), is(BASE_URL));

        sallyRow = driver.findElement(By.id("player-1"));
        assertTrue(sallyRow.getText().contains("Team2"));
    }

    private void findTradePlayersLinkAndClickIt() {
        List<WebElement> menuItem = driver.findElements(By.className("menuItem"));
        WebElement link = menuItem.get(2);

        assertEquals("Trade Players", link.getText());

        link.click();
    }

    private List<String> getDropDownOptionsFrom(WebElement dropDown) {
        List<String> dropDownOptions = new ArrayList<String>();
        Select select = new Select(dropDown);
        for (WebElement dropDownOption : select.getOptions()) {
            dropDownOptions.add(dropDownOption.getText());
        }

        return dropDownOptions;
    }
}
