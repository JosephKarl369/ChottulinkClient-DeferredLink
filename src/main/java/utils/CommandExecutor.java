package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public final class CommandExecutor {

    private CommandExecutor() {
    }

    public static void execute(List<String> command) {

        ProcessBuilder processBuilder = new ProcessBuilder(command);

        processBuilder.redirectErrorStream(true);

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();

            if (exitCode != 0) {

                throw new RuntimeException(
                        "Command execution failed.\nExit Code : "
                                + exitCode);

            }

        } catch (IOException | InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new RuntimeException(
                    "Unable to execute command.",
                    e
            );
        }
    }
}