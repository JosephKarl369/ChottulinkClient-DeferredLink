package tests;

import driver.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import server.AppiumServiceManager;
import utils.DeviceManager;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {

        // Verify at least one device is connected
        if (!DeviceManager.isDeviceConnected()) {
            throw new RuntimeException(
                    "No Android/iOS device connected."
            );
        }

        // Start Appium Server
        AppiumServiceManager.startAppiumServer();

    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {

        // Create Driver
        DriverFactory.initializeDriver();

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {

        // Quit Driver
        DriverFactory.quitDriver();

    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {

        // Stop Appium Server
        AppiumServiceManager.stopAppiumServer();

    }

}