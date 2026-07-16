package pages;

import config.ConfigReader;
import driver.DriverFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

import java.time.Duration;

public class DeferredLinkPage extends BasePage {

    public DeferredLinkPage() {

        PageFactory.initElements(new AppiumFieldDecorator(DriverFactory.getDriver(), Duration.ofSeconds(ConfigReader.getInt("explicitWait"))), this);
    }

    @AndroidFindBy(id = "religious.connect.app:id/tvLink")
    @iOSXCUITFindBy(accessibility = "tvLink")
    private WebElement deferredLinkURL;

    @AndroidFindBy(id = "religious.connect.app:id/tvShortLink")
    @iOSXCUITFindBy(accessibility = "tvShortLink")
    private WebElement shortLink;

    @AndroidFindBy(id = "religious.connect.app:id/tvShortLinkRaw")
    @iOSXCUITFindBy(accessibility = "tvDeferredLink")
    private WebElement clickedUrl;

    @AndroidFindBy(id = "religious.connect.app:id/tvIsDeferred")
    @iOSXCUITFindBy(accessibility = "tvIsDeferred")
    private WebElement isDeferred;

    @AndroidFindBy(id = "religious.connect.app:id/tvStatus")
    @iOSXCUITFindBy(accessibility = "tvStatus")
    private WebElement status;


    /**
     * Wait until Deferred Link screen is fully loaded.
     */
    public void waitForPageToLoad() {

        WaitUtils.waitForVisibility(deferredLinkURL);

    }

    public String getClickedUrl() {

        return clickedUrl.getText();

    }

    public String getShortLink() {

        return shortLink.getText();

    }

    public String getDeferredLink() {

        return deferredLinkURL.getText();

    }

    public boolean isDeferred() {

        return Boolean.parseBoolean(isDeferred.getText());

    }

    public String getStatus() {

        return status.getText();

    }

}