package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.springapp.mvc.functional.FileUploadHelper.PLAYER_LIST_2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TradeTest extends FunctionalBase {
    @Test
    public void shouldBeAbleToGoToTradePlayersPageFromHomePage() {
        driver.get(BASE_URL);
        helper.uploadFileFor("players", PLAYER_LIST_2);

        List<WebElement> menuItem = driver.findElements(By.className("menuItem"));
        WebElement link = menuItem.get(2);

        assertEquals("Trade Players", link.getText());

        link.click();

        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertEquals("Trade Players", h1.getText());

        assertTrue(driver.findElement(By.id("player-table")).getText().contains("Aubrey"));
    }
}
