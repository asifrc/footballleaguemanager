package com.springapp.mvc.functional;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TradeTest extends FunctionalBase {
    @Ignore("George: Asif, why did pulling your code break your own test!")
    @Test
    public void shouldBeAbleToGoToTradePlayersPageFromHomePage() throws Exception {
        driver.get(BASE_URL);
        List<WebElement> menuItem = driver.findElements(By.className("menuItem"));
        WebElement link = menuItem.get(2);

        assertEquals("Trade Players", link.getText());

        link.click();

        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertEquals("Trade Players", h1.getText());
    }
}
