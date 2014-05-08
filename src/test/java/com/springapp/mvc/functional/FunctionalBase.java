package com.springapp.mvc.functional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.springapp.mvc.functional.FileUploadHelper.*;

public class FunctionalBase {
    protected static final String BASE_URL = "http://localhost:8080/";
    protected static WebDriver driver;
    protected FileUploadHelper helper;

    @BeforeClass
    public static void openDriver() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void closeDriver() {
        driver.close();
    }

    @Before
    public void startHelper() throws Exception {
        helper = new FileUploadHelper(driver);
    }

    @After
    public void resetPlayerList() throws Exception {
        driver.get(BASE_URL);
        helper.uploadFileFor("players", PLAYER_LIST_EMPTY);
    }
}
