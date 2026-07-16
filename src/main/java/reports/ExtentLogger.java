package reports;

public final class ExtentLogger {

    private ExtentLogger() {
    }

    public static void info(String message) {

        ExtentManager.getExtentTest().info(message);

    }

    public static void pass(String message) {

        ExtentManager.getExtentTest().pass(message);

    }

    public static void fail(String message) {

        ExtentManager.getExtentTest().fail(message);

    }

    public static void warning(String message) {

        ExtentManager.getExtentTest().warning(message);

    }

    public static void skip(String message) {

        ExtentManager.getExtentTest().skip(message);

    }

    /**
     * Logs Expected vs Actual values.
     */
    public static void validation(String field, String expected, String actual) {

        if (expected.equals(actual)) {

            ExtentManager.getExtentTest().pass("<b>" + field + "</b><br>" + "Expected : " + expected + "<br>" + "Actual&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + actual);

        } else {

            ExtentManager.getExtentTest().fail("<b>" + field + "</b><br>" + "Expected : " + expected + "<br>" + "Actual&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: " + actual);

        }

    }

    /**
     * Logs boolean validations.
     */
    public static void validation(String field, boolean expected, boolean actual) {

        validation(field, String.valueOf(expected), String.valueOf(actual));

    }
}