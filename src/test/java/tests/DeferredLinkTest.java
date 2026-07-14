package tests;

import config.ConfigReader;
import driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ChromePage;
import pages.DeferredLinkPage;
import utils.installer.InstallerFactory;

import java.util.Map;

public class DeferredLinkTest extends BaseTest {

    @Test(description = "Verify Deferred Deep Link Flow")
    public void verifyDeferredDeepLink() {

        // Launch Chrome and trigger deferred link
        ChromePage chromePage = new ChromePage();

        chromePage.openWebsite();

        chromePage.clickOpenLink();

        // Verify Play Store / App Store is opened
        Assert.assertTrue(
                chromePage.isStoreOpened(),
                "Store application was not opened."
        );

        // Install the application
        InstallerFactory.getInstaller().install();

        // Launch installed application
        if (ConfigReader.get("platform").equalsIgnoreCase("Android")) {

            ((JavascriptExecutor) DriverFactory.getDriver())
                    .executeScript(
                            "mobile: activateApp",
                            Map.of(
                                    "appId",
                                    ConfigReader.get("android.appPackage")
                            )
                    );

        } else {

            ((JavascriptExecutor) DriverFactory.getDriver())
                    .executeScript(
                            "mobile: activateApp",
                            Map.of(
                                    "bundleId",
                                    ConfigReader.get("ios.bundleId")
                            )
                    );

        }

        // Verify Deferred Link
        DeferredLinkPage deferredLinkPage = new DeferredLinkPage();

        Assert.assertTrue(
                deferredLinkPage.verifyDeferredLink(
                        ConfigReader.get("expectedDeferredLink")
                ),
                "Deferred link verification failed."
        );

    }

}