package utils;

import config.ConfigReader;

public final class Downloader {

    private Downloader() {
    }

    public static String downloadAndroidApp() {

        // TODO Download APK from Server

        return ConfigReader.get("android.appPath");

    }

    public static String downloadIOSApp() {

        // TODO Download IPA from Server

        return ConfigReader.get("ios.appPath");

    }

}