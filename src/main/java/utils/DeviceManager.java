package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class DeviceManager {

    private DeviceManager() {
    }

    /**
     * Returns all connected Android device IDs.
     */
    public static List<String> getConnectedDevices() {

        List<String> devices = new ArrayList<>();

        try {

            Process process = new ProcessBuilder("adb", "devices").start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.endsWith("device")
                        && !line.startsWith("List")) {

                    devices.add(line.split("\\s+")[0]);

                }
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to fetch connected devices.",
                    e
            );
        }

        return devices;
    }

    /**
     * Returns true if at least one device is connected.
     */
    public static boolean isDeviceConnected() {

        return !getConnectedDevices().isEmpty();

    }

    /**
     * Returns the first connected Android device UDID.
     */
    public static String getAndroidDeviceId() {

        List<String> devices = getConnectedDevices();

        if (devices.isEmpty()) {

            throw new RuntimeException(
                    "No Android Device Connected."
            );

        }

        return devices.get(0);

    }

}