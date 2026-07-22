package execution;

import driver.DriverFactory;
import server.AppiumServiceManager;
import utils.DeviceManager;

public class LocalExecution implements ExecutionStrategy {

    @Override
    public void beforeSuite() {

        if (!DeviceManager.isDeviceConnected()) {
            throw new RuntimeException("No Android/iOS device connected.");
        }

        AppiumServiceManager.startAppiumServer();
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
        AppiumServiceManager.stopAppiumServer();
    }
}