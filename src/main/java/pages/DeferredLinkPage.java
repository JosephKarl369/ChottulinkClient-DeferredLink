package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class DeferredLinkPage extends BasePage {

    //Locators Android
    @AndroidFindBy(id = "religious.connect.app:id/tvLink")
    @iOSXCUITFindBy(accessibility = "tvLink")
    private WebElement clickedUrl;

    @AndroidFindBy(id = "religious.connect.app:id/tvShortLink")
    @iOSXCUITFindBy(accessibility = "tvShortLink")
    private WebElement shortLink;

    @AndroidFindBy(id = "religious.connect.app:id/tvLink")
    @iOSXCUITFindBy(accessibility = "tvLink")
    private WebElement deferredLink;

    @AndroidFindBy(id = "religious.connect.app:id/tvIsDeferred")
    @iOSXCUITFindBy(accessibility = "tvIsDeferred")
    private WebElement isDeferred;

    @AndroidFindBy(id = "religious.connect.app:id/tvStatus")
    @iOSXCUITFindBy(accessibility = "tvStatus")
    private WebElement status;


    public String getClickedUrl() {
        return clickedUrl.getText();
    }

    public String getShortLink() {
        return shortLink.getText();
    }

    public String getDeferredLink() {
        return deferredLink.getText();
    }

    public boolean isDeferred() {
        return Boolean.parseBoolean(isDeferred.getText());
    }

    public String getStatus() {
        return status.getText();
    }


}