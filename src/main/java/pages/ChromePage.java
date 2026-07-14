package pages;

import config.ConfigReader;
import driver.DriverFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

import java.util.Map;

public class ChromePage extends BasePage {

    private final By openLinkButton = AppiumBy.accessibilityId("open_link_button");

    public void openWebsite() {

        DriverFactory.getDriver().get(ConfigReader.get("htmlPage"));

    }

    public void clickOpenLink() {

        click(openLinkButton);

    }

    public boolean isStoreOpened() {

        if (DriverFactory.getDriver() instanceof AndroidDriver) {

            String packageName = ((AndroidDriver) DriverFactory.getDriver()).getCurrentPackage();

            return packageName.equals(ConfigReader.get("android.storePackage"));

        }

        Map<String, Object> appInfo = (Map<String, Object>) ((IOSDriver) DriverFactory.getDriver()).executeScript("mobile: activeAppInfo");

        return appInfo.get("bundleId").toString().equals(ConfigReader.get("ios.storeBundleId"));

    }

}