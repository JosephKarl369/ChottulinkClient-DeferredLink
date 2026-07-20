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
import reports.JsonConsoleReport;
import reports.ValidationReport;

import java.util.Map;

public class DeferredLinkTest extends BaseTest {

    @Test(description = "Verify Deferred Deep Link Flow")
    public void verifyDeferredDeepLink() {

        String platform = System.getProperty("platform", ConfigReader.get("platform"));

        // Ensure fresh install
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

        Assert.assertTrue(chromePage.isStoreOpened(), "Store application was not opened.");

        ExtentLogger.pass("Play Store opened successfully");

        // Install Application
        InstallerFactory.getInstaller().install();

        ExtentLogger.pass("Application installed");

        // Launch Installed App
        if (platform.equalsIgnoreCase("Android")) {

            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("mobile: activateApp", Map.of("appId", ConfigReader.get("android.appPackage")));

        } else {

            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("mobile: activateApp", Map.of("bundleId", ConfigReader.get("ios.bundleId")));
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
        // Validation + Reporting
        //==========================

        JsonConsoleReport.clear();

        boolean result = true;

        result &= ValidationReport.verify("Clicked URL", expectedClickedUrl, actualClickedUrl);

        result &= ValidationReport.verify("Short Link", expectedShortLink, actualShortLink);

        result &= ValidationReport.verify("Deferred Link", expectedDeferredLink, actualDeferredLink);

        result &= ValidationReport.verify("Is Deferred", true, actualIsDeferred);

        result &= ValidationReport.verify("Status", expectedStatus, actualStatus);

        // Print JSON report in Jenkins Console
        JsonConsoleReport.print();

        // Final Assertion
        Assert.assertTrue(result, "Deferred Deep Link validation failed.");

        ExtentLogger.pass("Deferred Deep Link verification completed successfully.");
    }
}