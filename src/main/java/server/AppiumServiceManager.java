package server;

import config.ConfigReader;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.URL;

public final class AppiumServiceManager {

    private static AppiumDriverLocalService service;

    private AppiumServiceManager() {
    }

    private static void validateEnvironment() {

        String nodePath = ConfigReader.get("nodePath");
        String appiumJsPath = ConfigReader.get("appiumJsPath");

        System.out.println("Node Path      : " + nodePath);
        System.out.println("Appium JS Path : " + appiumJsPath);

        File node = new File(nodePath);
        File appiumJs = new File(appiumJsPath);

        System.out.println("Node Exists    : " + node.exists());
        System.out.println("Appium Exists  : " + appiumJs.exists());

        if (!node.exists()) {
            throw new RuntimeException("Node executable not found: " + node.getAbsolutePath());
        }

        if (!appiumJs.exists()) {
            throw new RuntimeException("Appium main.js not found: " + appiumJs.getAbsolutePath());
        }
    }

    public static void startAppiumServer() {

        if (isAwsExecution()) {
            System.out.println("AWS Execution detected. Skipping local Appium Server startup.");
            return;
        }

        if (service == null || !service.isRunning()) {

            validateEnvironment();

            service = new AppiumServiceBuilder().usingDriverExecutable(new File(ConfigReader.get("nodePath"))).withAppiumJS(new File(ConfigReader.get("appiumJsPath"))).usingAnyFreePort().build();

            service.start();

            System.out.println("Appium Server Started : " + service.getUrl());
        }
    }

    public static void stopAppiumServer() {

        if (isAwsExecution()) {
            return;
        }

        if (service != null && service.isRunning()) {
            service.stop();
        }
    }

    public static URL getAppiumServerUrl() {

        try {

            if (isAwsExecution()) {
                return new URL(ConfigReader.get("aws.appium.url"));
            }

            if (service == null) {
                throw new RuntimeException("Appium Server is not started.");
            }

            return service.getUrl();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean isAwsExecution() {
        return "aws".equalsIgnoreCase(ConfigReader.get("execution"));
    }
}
