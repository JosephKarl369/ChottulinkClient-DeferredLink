package tests;

import driver.DriverFactory;
import org.testng.annotations.*;
import server.AppiumServiceManager;
import utils.DeviceManager;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {

        // Verify at least one device is connected
        if (!DeviceManager.isDeviceConnected()) {
            throw new RuntimeException("No Android/iOS device connected.");
        }

        // Start Appium Server
        AppiumServiceManager.startAppiumServer();
    }

    @Parameters("platform")
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(@Optional("Android") String platform) {

        // Set platform for DriverFactory
        System.setProperty("platform", platform);

        // Create Appium Driver
        DriverFactory.initializeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {

        DriverFactory.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {

        AppiumServiceManager.stopAppiumServer();
    }
}