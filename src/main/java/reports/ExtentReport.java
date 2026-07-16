package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReport {

    private static ExtentReports extentReports;

    private ExtentReport() {
    }

    public static void initReports() {

        if (extentReports == null) {

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/ExtentReport.html");

            sparkReporter.config().setReportName("Deferred Link Automation");

            sparkReporter.config().setDocumentTitle("Automation Execution Report");

            extentReports = new ExtentReports();

            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo("Framework", "Appium");

            extentReports.setSystemInfo("Language", "Java");

            extentReports.setSystemInfo("Automation", "Deferred Link");

        }

    }

    public static ExtentReports getExtent() {

        return extentReports;

    }

    public static void flushReports() {

        if (extentReports != null) {

            extentReports.flush();

        }

    }

}