package execution;

import driver.DriverFactory;

public class AwsExecution implements ExecutionStrategy {

    @Override
    public void beforeSuite() {
        // AWS Device Farm provides the device and Appium server.
    }

    @Override
    public void beforeMethod() {
        DriverFactory.initializeDriver();
    }

    @Override
    public void afterMethod() {
        DriverFactory.quitDriver();
    }

    @Override
    public void afterSuite() {
        // Nothing to clean up.
    }
}