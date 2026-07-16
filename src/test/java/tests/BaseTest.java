package tests;

import driver.DriverFactory;
import listeners.TestListener;
import org.testng.annotations.*;
import server.AppiumServiceManager;
import utils.DeviceManager;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {

        if (!DeviceManager.isDeviceConnected()) {
            throw new RuntimeException("No Android/iOS device connected.");
        }

        AppiumServiceManager.startAppiumServer();

    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("platform")
    public void beforeMethod(@Optional("Android") String platform) {

        // Command line value overrides XML value
        String runPlatform = System.getProperty("platform", platform);

        System.setProperty("platform", runPlatform);

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