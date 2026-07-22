package driver;

import config.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import server.AppiumServiceManager;

import java.net.MalformedURLException;
import java.net.URL;

public final class DriverFactory {

    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void initializeDriver() {

        String platform = System.getProperty("platform", ConfigReader.get("platform"));
        String execution = System.getProperty("execution", ConfigReader.get("execution"));


        switch (platform.toLowerCase()) {

            case "android":

                UiAutomator2Options androidOptions = new UiAutomator2Options();

                if ("aws".equalsIgnoreCase(execution)) {

                    // AWS Device Farm chooses the device.
                    androidOptions.setPlatformName("Android");
                    androidOptions.setAutomationName("UiAutomator2");
                } else {

                    androidOptions.setPlatformName(ConfigReader.get("android.platformName"));

                    androidOptions.setDeviceName(ConfigReader.get("android.deviceName"));

                    androidOptions.setPlatformVersion(ConfigReader.get("android.platformVersion"));

                    androidOptions.setAutomationName(ConfigReader.get("android.automationName"));
                }

                // Launch Chrome
                androidOptions.setAppPackage("com.android.chrome");
                androidOptions.setAppActivity("com.google.android.apps.chrome.Main");

                driver.set(new AndroidDriver(getServerUrl(), androidOptions));

                break;

            case "ios":

                XCUITestOptions iosOptions = new XCUITestOptions();

                if ("aws".equalsIgnoreCase(execution)) {
                    iosOptions.setPlatformName("iOS");
                    iosOptions.setAutomationName("XCUITest");

                } else {

                    iosOptions.setPlatformName(ConfigReader.get("ios.platformName"));

                    iosOptions.setDeviceName(ConfigReader.get("ios.deviceName"));

                    iosOptions.setPlatformVersion(ConfigReader.get("ios.platformVersion"));

                    iosOptions.setAutomationName(ConfigReader.get("ios.automationName"));
                }

                // Launch Safari
                iosOptions.setCapability("browserName", "Safari");

                driver.set(new IOSDriver(getServerUrl(), iosOptions));

                break;

            default:

                throw new RuntimeException("Unsupported Platform : " + platform);
        }

    }

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    private static URL getServerUrl() {

        try {

            String execution = System.getProperty("execution", ConfigReader.get("execution"));

            if ("aws".equalsIgnoreCase(execution)) {

                return new URL(ConfigReader.get("aws.appium.url"));

            }

            return AppiumServiceManager.getAppiumServerUrl();

        } catch (MalformedURLException e) {

            throw new RuntimeException("Invalid Appium Server URL", e);

        }

    }

    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();

            driver.remove();

        }

    }

}