package server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.net.URL;

public final class AppiumServiceManager {

    public static AppiumDriverLocalService service;

    public static void startAppiumServer() {
        if (service == null | !service.isRunning()) {
            service = new AppiumServiceBuilder()
                    .usingAnyFreePort()
                    .build();
            service.start();
            System.out.println("Started Appium Server On:" + service.getUrl());
        }

    }

    public static void stopAppiumServer() {
        if(service != null| service.isRunning()) {
            service.stop();
            System.out.println("Stopped Appium Server On:" + service.getUrl());
        }
    }

    public static URL getAppiumServerUrl() {
        return service.getUrl();
    }
}
