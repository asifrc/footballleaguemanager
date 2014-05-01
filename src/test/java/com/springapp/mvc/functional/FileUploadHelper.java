package com.springapp.mvc.functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class FileUploadHelper {
    private static final String TEST_ROOT = "./src/test/java/com/springapp/mvc/";
    public static final String PLAYER_LIST_2 = TEST_ROOT + "playerList2.txt";
    public static final String PLAYER_LIST_1 = TEST_ROOT + "playerList1.txt";
    public static final String PLAYER_LIST_EMPTY = TEST_ROOT + "playerListEmpty.txt";
    public static final String BASE_URL = "http://localhost:8080/";
    WebDriver driver;

    public FileUploadHelper(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void uploadFile(String relativeFilePath) {
        driver.get(BASE_URL);
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
