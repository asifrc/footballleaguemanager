package com.springapp.mvc.functional;

import com.springapp.mvc.model.Player;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;

public class HomeTest {
    private static final String BASE_URL = "http://localhost:8080/";
    private WebDriver driver = new HtmlUnitDriver();

    @Ignore("#3 | Asif | Needs file upload integrated, since PlayerService now returns empty playerlist by default")
    @Test
    public void shouldListPlayerName() throws Exception {
        Player bob = new Player("Bob", "0");

        driver.get(BASE_URL);
        WebElement player_1 = driver.findElement(By.id("player-1"));

        assertEquals("Bob", player_1.getText());
    }

    @Ignore("#3 | Asif | Needs file upload integrated, since PlayerService now returns empty playerlist by default")
    @Test
    public void shouldListTwoPlayerNames() {
        Player bob = new Player("Bob", "0");
        Player sally = new Player("Sally", "1");

        driver.get(BASE_URL);
        WebElement player_list = driver.findElement(By.id("player-list"));

        assertEquals("Bob", player_list.findElement(By.id("player-1")).getText());
        assertEquals("Sally", player_list.findElement(By.id("player-2")).getText());
    }
}
