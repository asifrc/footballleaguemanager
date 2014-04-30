package com.springapp.mvc.functional;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class FindPlayerTest {
    private static final String BASE_URL = "http://localhost:8080/";
    private static final String PLAYER_LIST_EMPTY = "./src/test/java/com/springapp/mvc/playerListEmpty.txt";
    public static final String PLAYER_LIST_1 = "./src/test/java/com/springapp/mvc/playerList1.txt";

    WebDriver driver = new HtmlUnitDriver();

    @Before
    public void setUp() throws Exception {
        driver.get(BASE_URL);
        uploadFile(PLAYER_LIST_EMPTY);
    }

    @Test
    public void shouldHaveAPlayerNameTextbox() throws Exception {
        driver.get(BASE_URL);

        uploadFile(PLAYER_LIST_1);


        driver.get(BASE_URL + "find");

        WebElement nameTextBox = driver.findElement(By.id("name"));
        WebElement numberTextBox = driver.findElement(By.id("number"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        nameTextBox.sendKeys("Sally");
        numberTextBox.sendKeys("0");
        submitButton.click();

        WebElement player = driver.findElement(By.id("playerText"));
        assertEquals("#0 Sally", player.getText());
    }

    private void uploadFile(String relativeFilePath) {
        WebElement uploadField = driver.findElement(By.id("upload-field"));
        uploadField.sendKeys(getAbsolutePath(relativeFilePath));

        WebElement uploadButton = driver.findElement(By.id("upload-button"));
        uploadButton.click();
    }

    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        return file.getAbsolutePath();
    }
}
