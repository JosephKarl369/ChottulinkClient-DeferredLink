package listeners;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentLogger;
import reports.ExtentManager;
import reports.ExtentReport;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {

        System.out.println("Initializing Extent Report...");

        ExtentReport.initReports();

    }

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("Listener Started : "
                + result.getMethod().getMethodName());

        ExtentTest test = ExtentReport.getExtent()
                .createTest(
                        result.getMethod().getDescription() != null
                                ? result.getMethod().getDescription()
                                : result.getMethod().getMethodName());

        ExtentManager.setExtentTest(test);

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentLogger.pass("Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentLogger.fail(result.getThrowable().getMessage());

    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentLogger.skip("Test Skipped");

    }

    @Override
    public void onFinish(ITestContext context) {

        ExtentReport.flushReports();

        ExtentManager.unload();

    }

}