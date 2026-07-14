package pages;

import org.openqa.selenium.By;
import utils.WaitUtils;

public class BasePage {

    protected void click(By locator) {
        WaitUtils.waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WaitUtils.waitForVisibility(locator).sendKeys(text);
    }

    protected boolean isDisplayed(By locator) {
        return WaitUtils.waitForVisibility(locator).isDisplayed();
    }

    protected String getText(By locator) {
        return WaitUtils.waitForVisibility(locator).getText();
    }
}
