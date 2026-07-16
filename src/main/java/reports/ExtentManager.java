package reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {

    private ExtentManager() {
    }

    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void setExtentTest(ExtentTest test) {

        extentTest.set(test);

    }

    public static ExtentTest getExtentTest() {

        return extentTest.get();

    }

    public static void unload() {

        extentTest.remove();

    }

}