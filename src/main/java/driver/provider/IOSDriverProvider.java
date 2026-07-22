package driver.provider;

import config.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import server.AppiumServiceManager;

public class IOSDriverProvider implements DriverProvider {

    @Override
    public AppiumDriver createDriver() {

        XCUITestOptions options = new XCUITestOptions();

        options.setPlatformName(
                ConfigReader.get("ios.platformName"));

        options.setDeviceName(
                ConfigReader.get("ios.deviceName"));

        options.setPlatformVersion(
                ConfigReader.get("ios.platformVersion"));

        options.setAutomationName(
                ConfigReader.get("ios.automationName"));

        options.setCapability("browserName", "Safari");

        return new IOSDriver(
                AppiumServiceManager.getAppiumServerUrl(),
                options
        );

    }

}