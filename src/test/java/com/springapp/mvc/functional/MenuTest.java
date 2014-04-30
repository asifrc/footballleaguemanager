package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MenuTest {
    public static final String BASE_URL = "http://localhost:8080/";
    WebDriver driver = new HtmlUnitDriver();

    @Test
    public void shouldHaveAListPlayersLink() throws Exception {
        driver.get(BASE_URL);
        List<WebElement> menuItems = driver.findElements(By.className("menuItem"));

        String name = "List Players";

        WebElement link = getMenuItems(menuItems, name);
        assertEquals(name, link.getText());

        link.click();
        assertEquals(BASE_URL, driver.getCurrentUrl());
    }

    @Test
    public void shouldHaveAFindPlayersLink() throws Exception {
        driver.get(BASE_URL);
        List<WebElement> menuItems = driver.findElements(By.className("menuItem"));

        String name = "Find Players";

        WebElement link = getMenuItems(menuItems, name);
        assertEquals(name, link.getText());

        link.click();
        assertEquals(BASE_URL + "find", driver.getCurrentUrl());
    }

    private WebElement getMenuItems(List<WebElement> menuItems, String name) {
        WebElement item = null;
        for (WebElement menuItem : menuItems) {
            if (menuItem.getText().equals(name)) {
                item = menuItem;
            }
        }
        return item;
    }
}
