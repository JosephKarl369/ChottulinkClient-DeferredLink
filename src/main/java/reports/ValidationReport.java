package reports;

public final class ValidationReport {

    private ValidationReport() {
    }

    public static boolean verify(String field, Object expected, Object actual) {

        boolean pass = JsonConsoleReport.add(field, expected, actual);

        String message = "<b>" + field + "</b><br>" + "Expected : " + expected + "<br>Actual : " + actual + "<br>Result : " + (pass ? "PASS" : "FAIL");

        if (pass) {
            ExtentLogger.pass(message);
        } else {
            ExtentLogger.fail(message);
        }

        return pass;
    }
}