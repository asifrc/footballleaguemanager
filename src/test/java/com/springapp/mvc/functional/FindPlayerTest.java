package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class FindPlayerTest {
    private static final String BASE_URL = "http://localhost:8080/";

    WebDriver driver = new HtmlUnitDriver();

    @Test
    public void shouldHaveAPlayerNameTextbox() throws Exception {
        driver.get(BASE_URL + "find");
        WebElement nameTextBox = driver.findElement(By.id("name"));
        WebElement numberTextBox = driver.findElement(By.id("number"));
        WebElement submitButton = driver.findElement(By.id("submit"));
    }
}
