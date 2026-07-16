package pages;

import driver.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import utils.WaitUtils;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class BasePage {

    protected void click(By locator) {
        WaitUtils.waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WaitUtils.waitForVisibility(locator).sendKeys(text);
    }

    protected void sendKeys(By locator, String text) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return WaitUtils.waitForVisibility(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        return WaitUtils.waitForVisibility(locator).isDisplayed();
    }

    protected void tap(By locator) {

        WebElement element = WaitUtils.waitForVisibility(locator);

        Point point = element.getLocation();
        Dimension size = element.getSize();

        int x = point.getX() + size.getWidth() / 2;
        int y = point.getY() + size.getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                x,
                y));

        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        tap.addAction(new Pause(finger, Duration.ofMillis(100)));

        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        DriverFactory.getDriver().perform(List.of(tap));
    }
}