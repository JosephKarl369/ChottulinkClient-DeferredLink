package utils.installer;

import config.ConfigReader;

public final class InstallerFactory {

    private InstallerFactory() {
    }

    public static AppInstaller getInstaller() {

        String platform = ConfigReader.get("platform");

        switch (platform.toLowerCase()) {

            case "android":
                return new AndroidInstaller();

            case "ios":
                return new IOSInstaller();

            default:
                throw new RuntimeException("Unsupported Platform : " + platform);

        }

    }

}