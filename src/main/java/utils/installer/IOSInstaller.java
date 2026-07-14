package utils.installer;

import config.ConfigReader;
import utils.CommandExecutor;
import utils.Downloader;

import java.util.List;

public class IOSInstaller implements AppInstaller {

    @Override
    public void install() {

        String ipaPath;

        if (ConfigReader.get("appSource").equalsIgnoreCase("LOCAL")) {

            ipaPath = ConfigReader.get("ios.appPath");

        } else {

            ipaPath = Downloader.downloadIOSApp();

        }

        CommandExecutor.execute(

                List.of(

                        "xcrun",

                        "simctl",

                        "install",

                        "booted",

                        ipaPath

                )

        );

        System.out.println("iOS App Installed Successfully.");

    }

}