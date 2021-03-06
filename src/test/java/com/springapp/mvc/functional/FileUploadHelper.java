package com.springapp.mvc.functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class FileUploadHelper {
    private static final String TEST_ROOT = "./src/test/java/com/springapp/mvc/";
    public static final String PLAYER_FILE_1 = TEST_ROOT + "playerFile1.txt";
    public static final String PLAYER_FILE_2 = TEST_ROOT + "playerFile2.txt";
    public static final String EMPTY_TEXT_FILE = TEST_ROOT + "empty.txt";
    public static final String COACH_FILE = TEST_ROOT + "coachFile.txt";
    WebDriver driver;

    public FileUploadHelper(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void uploadFileFor(String personType, String relativeFilePath) {
        WebElement uploadField = findUploadFieldFor(personType);
        WebElement uploadButton = findUploadButtonFor(personType);

        uploadField.sendKeys(getAbsolutePath(relativeFilePath));

        uploadButton.click();
    }

    private WebElement findUploadFieldFor(String personType) {
        return driver.findElement(By.id(personType)).findElement(By.className("upload-field"));
    }

    private WebElement findUploadButtonFor(String personType) {
        return driver.findElement(By.id(personType)).findElement(By.className("upload-button"));
    }

    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        return file.getAbsolutePath();
    }
}
