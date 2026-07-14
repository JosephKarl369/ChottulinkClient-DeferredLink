package utils.installer;

import config.ConfigReader;
import utils.CommandExecutor;
import utils.DeviceManager;
import utils.Downloader;

import java.util.List;

public class AndroidInstaller implements AppInstaller {

    @Override
    public void install() {

        String apkPath;

        if (ConfigReader.get("appSource").equalsIgnoreCase("LOCAL")) {

            apkPath = ConfigReader.get("android.appPath");

        } else {

            apkPath = Downloader.downloadAndroidApp();

        }

        CommandExecutor.execute(

                List.of(

                        "adb",

                        "-s",

                        DeviceManager.getAndroidDeviceId(),

                        "install",

                        "-r",

                        apkPath

                )

        );

        System.out.println("Android App Installed Successfully.");

    }

}