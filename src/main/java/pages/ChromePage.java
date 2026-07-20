package pages;

import config.ConfigReader;
import driver.DriverFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

import java.util.Map;

public class ChromePage extends BasePage {

    // Chrome Locators
    private final By searchBox = AppiumBy.id("com.android.chrome:id/search_box_text");

    private final By addressBar = AppiumBy.id("com.android.chrome:id/url_bar");

    // Web Page Button
    private final By openLinkButton = By.xpath("//android.widget.Button[@resource-id='openBtn']");

    // Chrome First Run
    private final By dismissSignInButton = AppiumBy.id("com.android.chrome:id/signin_fre_dismiss_button");

    private final By negativeButton = AppiumBy.id("com.android.chrome:id/negative_button");

    private final By gotItButton = AppiumBy.id("com.android.chrome:id/ack_button");

    private final By notFoundPage = AppiumBy.id("android.widget.TextView");


    /**
     * Handles Chrome first launch screens.
     */
    public void handleChromeConfiguration() {

        clickIfPresent(dismissSignInButton);

        clickIfPresent(negativeButton);

        clickIfPresent(gotItButton);

    }

    /**
     * Opens the test website.
     */
    public void openWebsite() {

        click(searchBox);

        WebElement url = WaitUtils.waitForVisibility(addressBar);

        url.clear();

        url.sendKeys(ConfigReader.get("htmlPage"));

        ((AndroidDriver) DriverFactory.getDriver()).executeScript("mobile: performEditorAction", Map.of("action", "go"));

        try {
            Thread.sleep(5000);
            System.out.println(DriverFactory.getDriver().getPageSource());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WaitUtils.waitForVisibility(openLinkButton);

    }

    /**
     * Click Deferred Link button.
     */
    public void clickOpenLink() {

        WebElement button = WaitUtils.waitForVisibility(openLinkButton);

        System.out.println("Displayed : " + button.isDisplayed());
        System.out.println("Enabled   : " + button.isEnabled());
        System.out.println("Location  : " + button.getLocation());
        System.out.println("Size      : " + button.getSize());

        tap(openLinkButton);

    }

    /**
     * Verify Store Opened
     */
    @SuppressWarnings("unchecked")
    public boolean isStoreOpened() {

        if (DriverFactory.getDriver() instanceof AndroidDriver) {

            AndroidDriver driver = (AndroidDriver) DriverFactory.getDriver();

            for (int i = 0; i < 15; i++) {

                String currentPackage = driver.getCurrentPackage();

                System.out.println("Current Package : " + currentPackage);

                if (currentPackage.equals(ConfigReader.get("android.storePackage"))) {
                    return true;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            return false;
        }

        Map<String, Object> appInfo = (Map<String, Object>) ((IOSDriver) DriverFactory.getDriver()).executeScript("mobile: activeAppInfo");

        String bundleId = appInfo.get("bundleId").toString();

        System.out.println("Current Bundle : " + bundleId);

        return bundleId.equals(ConfigReader.get("ios.storeBundleId"));
    }

    private void clickIfPresent(By locator) {

        try {

            WaitUtils.waitForClickable(locator).click();


        } catch (Exception ignored) {

        }

    }

}
