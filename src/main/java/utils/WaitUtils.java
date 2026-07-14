package utils;

import config.ConfigReader;
import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {

    private WaitUtils() {
    }

    /**
     * Creates WebDriverWait instance
     */
    private static WebDriverWait getWait() {

        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigReader.getInt("explicitWait")));
    }

    // ==========================
    // Visibility
    // ==========================

    public static WebElement waitForVisibility(By locator) {

        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForVisibility(WebElement element) {

        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    // ==========================
    // Clickable
    // ==========================

    public static WebElement waitForClickable(By locator) {

        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForClickable(WebElement element) {

        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    // ==========================
    // Presence
    // ==========================

    public static WebElement waitForPresence(By locator) {

        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ==========================
    // Invisibility
    // ==========================

    public static boolean waitForInvisibility(WebElement element) {

        return getWait().until(ExpectedConditions.invisibilityOf(element));
    }

    // ==========================
    // Text
    // ==========================

    public static boolean waitForText(WebElement element, String text) {

        return getWait().until(ExpectedConditions.textToBePresentInElement(element, text));
    }

}
