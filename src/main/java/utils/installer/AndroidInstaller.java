package utils.installer;

import config.ConfigReader;
import utils.CommandExecutor;
import utils.DeviceManager;
import utils.Downloader;

import java.util.List;

public class AndroidInstaller implements AppInstaller {

    @Override
    public void install() {

        String execution = System.getProperty("execution", ConfigReader.get("execution"));

        String apkPath;

        if ("aws".equalsIgnoreCase(execution)) {

            apkPath = System.getenv("DEVICEFARM_APP_PATH");

        } else if (ConfigReader.get("appSource").equalsIgnoreCase("LOCAL")) {

            apkPath = ConfigReader.get("android.appPath");

        } else {

            apkPath = Downloader.downloadAndroidApp();
        }

        System.out.println("Installing APK : " + apkPath);

        CommandExecutor.execute(List.of("adb", "-s", DeviceManager.getAndroidDeviceId(), "install", "-r", apkPath));

        System.out.println("Android App Installed Successfully.");
    }

    @Override
    public void uninstall() {

        String packageName = ConfigReader.get("android.appPackage");

        if (!isAppInstalled(packageName)) {
            System.out.println("App is not installed. Skipping uninstall.");
            return;
        }

        CommandExecutor.execute(List.of("adb", "-s", DeviceManager.getAndroidDeviceId(), "uninstall", packageName));

        System.out.println("Android App Uninstalled Successfully.");
    }

    public boolean isAppInstalled(String packageName) {

        String output = CommandExecutor.executeAndGetOutput(List.of("adb", "-s", DeviceManager.getAndroidDeviceId(), "shell", "pm", "list", "packages", packageName));

        return output.contains(packageName);
    }
}