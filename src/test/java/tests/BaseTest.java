package tests;

import driver.DriverFactory;
import execution.ExecutionManager;
import listeners.TestListener;
import org.testng.annotations.*;
import server.AppiumServiceManager;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {

        ExecutionManager.getStrategy().beforeSuite();

    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("platform")
    public void beforeMethod(@Optional("Android") String platform) {

        String runPlatform = System.getProperty("platform", platform);

        System.setProperty("platform", runPlatform);

        ExecutionManager.getStrategy().beforeMethod();

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {

        ExecutionManager.getStrategy().afterMethod();

    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {

        ExecutionManager.getStrategy().afterSuite();

    }
}