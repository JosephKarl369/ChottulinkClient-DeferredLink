package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class DeferredLinkPage extends BasePage {

    // Dummy Locator

    private final By deferredLinkText =
            AppiumBy.accessibilityId("deferred_link");

    public String getDeferredLink() {

        return getText(deferredLinkText);

    }

    public boolean verifyDeferredLink(String expectedLink) {

        return getDeferredLink().equalsIgnoreCase(expectedLink);

    }

}