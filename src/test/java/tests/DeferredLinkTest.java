package tests;

import config.ConfigReader;
import driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ChromePage;
import pages.DeferredLinkPage;
import reports.ExtentLogger;
import utils.installer.InstallerFactory;

import java.util.Map;

public class DeferredLinkTest extends BaseTest {

    @Test(description = "Verify Deferred Deep Link Flow")
    public void verifyDeferredDeepLink() {

        String platform = System.getProperty(
                "platform",
                ConfigReader.get("platform")
        );

        // Ensure fresh install for Deferred Deep Link
        InstallerFactory.getInstaller().uninstall();

        ChromePage chromePage = new ChromePage();

        ExtentLogger.info("Opening Chrome");

        chromePage.handleChromeConfiguration();

        chromePage.openWebsite();

        chromePage.clickOpenLink();

        // Debug - Wait until Play Store opens
        AndroidDriver driver = (AndroidDriver) DriverFactory.getDriver();

        for (int i = 0; i < 10; i++) {

            System.out.println("Package : " + driver.getCurrentPackage());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        ExtentLogger.pass("Deferred Link clicked");

        Assert.assertTrue(
                chromePage.isStoreOpened(),
                "Store application was not opened."
        );

        ExtentLogger.pass("Play Store opened successfully");

        // Install App
        InstallerFactory.getInstaller().install();

        ExtentLogger.pass("Application installed");

        // Launch Installed App
        if (platform.equalsIgnoreCase("Android")) {

            ((JavascriptExecutor) DriverFactory.getDriver())
                    .executeScript(
                            "mobile: activateApp",
                            Map.of("appId", ConfigReader.get("android.appPackage"))
                    );

        } else {

            ((JavascriptExecutor) DriverFactory.getDriver())
                    .executeScript(
                            "mobile: activateApp",
                            Map.of("bundleId", ConfigReader.get("ios.bundleId"))
                    );
        }

        // Wait for App to load
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        DeferredLinkPage deferredLinkPage = new DeferredLinkPage();

        //==========================
        // Actual Values
        //==========================

        String actualClickedUrl = deferredLinkPage.getClickedUrl();
        String actualShortLink = deferredLinkPage.getShortLink();
        String actualDeferredLink = deferredLinkPage.getDeferredLink();
        boolean actualIsDeferred = deferredLinkPage.isDeferred();
        String actualStatus = deferredLinkPage.getStatus();

        //==========================
        // Expected Values
        //==========================

        String expectedClickedUrl = ConfigReader.get("expectedClickedUrl");
        String expectedShortLink = ConfigReader.get("expectedShortLink");
        String expectedDeferredLink = ConfigReader.get("expectedDeferredLink");
        String expectedStatus = ConfigReader.get("expectedStatus");

        //==========================
        // Report Logging
        //==========================

        ExtentLogger.info(
                "Clicked URL<br>Expected : " + expectedClickedUrl +
                        "<br>Actual : " + actualClickedUrl
        );

        ExtentLogger.info(
                "Short Link<br>Expected : " + expectedShortLink +
                        "<br>Actual : " + actualShortLink
        );

        ExtentLogger.info(
                "Deferred Link<br>Expected : " + expectedDeferredLink +
                        "<br>Actual : " + actualDeferredLink
        );

        ExtentLogger.info(
                "Is Deferred<br>Expected : true" +
                        "<br>Actual : " + actualIsDeferred
        );

        ExtentLogger.info(
                "Status<br>Expected : " + expectedStatus +
                        "<br>Actual : " + actualStatus
        );

        //==========================
        // Assertions
        //==========================

        Assert.assertEquals(actualClickedUrl, expectedClickedUrl);

        Assert.assertEquals(actualShortLink, expectedShortLink);

        Assert.assertEquals(actualDeferredLink, expectedDeferredLink);

        Assert.assertTrue(actualIsDeferred);

        Assert.assertEquals(actualStatus, expectedStatus);

        ExtentLogger.pass("Deferred Deep Link verification completed successfully.");
    }
}