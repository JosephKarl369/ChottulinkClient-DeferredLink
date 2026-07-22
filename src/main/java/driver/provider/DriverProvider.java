package driver.provider;

import io.appium.java_client.AppiumDriver;

public interface DriverProvider {

    AppiumDriver createDriver();

}