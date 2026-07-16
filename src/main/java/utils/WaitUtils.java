package utils;

import config.ConfigReader;
import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {

    private static final int EXPLICIT_WAIT = ConfigReader.getInt("explicitWait");

    private WaitUtils() {
    }

    private static WebDriverWait getWait() {

        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT));

    }

    public static WebElement waitForVisibility(By locator) {

        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public static WebElement waitForClickable(By locator) {

        return getWait().until(ExpectedConditions.elementToBeClickable(locator));

    }

    public static WebElement waitForPresence(By locator) {

        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    public static boolean waitForText(WebElement element, String text) {

        return getWait().until(ExpectedConditions.textToBePresentInElement(element, text));

    }

    public static boolean waitForInvisibility(WebElement element) {

        return getWait().until(ExpectedConditions.invisibilityOf(element));

    }

    public static void waitForVisibility(WebElement element) {

        getWait().until(ExpectedConditions.visibilityOf(element));

    }

}