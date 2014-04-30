package com.springapp.mvc.functional;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertNotNull;

public class UploadPlayerListTest {
    public static final String BASE_URL = "http://localhost:8080/";
    WebDriver driver = new HtmlUnitDriver();

    @Test
    public void shouldHaveFileUploadRelatedButtons() throws Exception {
        driver.get(BASE_URL);

        WebElement browseButton = driver.findElement(By.id("upload-field"));
        WebElement uploadButton = driver.findElement(By.id("upload-button"));

        assertNotNull(browseButton);
        assertNotNull(uploadButton);
    }

}
