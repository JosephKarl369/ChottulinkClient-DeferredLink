package execution;

import config.ConfigReader;

public final class ExecutionManager {

    private static final ExecutionStrategy strategy;

    static {

        try {

            String execution = System.getProperty(
                    "execution",
                    ConfigReader.get("execution")
            );
            System.out.println("System execution property = " + System.getProperty("execution"));
            System.out.println("Config execution property = " + ConfigReader.get("execution"));
            System.out.println("Resolved execution = " + execution);

            ExecutionMode mode = ExecutionMode.valueOf(
                    execution.trim().toUpperCase()
            );
            System.out.println("Execution Mode = " + mode);

            switch (mode) {

                case AWS:
                    strategy = new AwsExecution();
                    break;

                case LOCAL:
                default:
                    strategy = new LocalExecution();
                    break;
            }

        } catch (IllegalArgumentException e) {

            throw new RuntimeException(
                    "Invalid execution mode. Supported values are: LOCAL, AWS",
                    e
            );

        }

    }

    private ExecutionManager() {
    }

    public static ExecutionStrategy getStrategy() {
        return strategy;
    }
}