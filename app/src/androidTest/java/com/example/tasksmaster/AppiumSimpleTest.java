package com.example.tasksmaster;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumSimpleTest {

    private AndroidDriver driver;

    @Before
    public void setUp() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("emulator-5554")
                .setNewCommandTimeout(Duration.ofHours(1));

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sampleTest() {
        try {
            WebElement button = driver.findElement(AppiumBy.accessibilityId("Add"));
            button.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
