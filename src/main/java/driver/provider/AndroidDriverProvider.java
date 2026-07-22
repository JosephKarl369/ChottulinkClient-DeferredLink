package driver.provider;

import config.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import server.AppiumServiceManager;

public class AndroidDriverProvider implements DriverProvider {

    @Override
    public AppiumDriver createDriver() {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(
                ConfigReader.get("android.platformName"));

        options.setDeviceName(
                ConfigReader.get("android.deviceName"));

        options.setPlatformVersion(
                ConfigReader.get("android.platformVersion"));

        options.setAutomationName(
                ConfigReader.get("android.automationName"));

        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");

        return new AndroidDriver(
                AppiumServiceManager.getAppiumServerUrl(),
                options
        );

    }

}