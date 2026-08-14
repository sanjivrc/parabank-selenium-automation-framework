package com.parabank.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Wraps ExtentReports setup so both the listener and (if needed) individual
 * tests can log into the same report instance. One ExtentReports object is
 * shared across the whole suite; one ExtentTest per test method.
 */
public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/ExtentReport.html");
            sparkReporter.config().setDocumentTitle("ParaBank Automation Report");
            sparkReporter.config().setReportName("ParaBank Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Application", "ParaBank");
            extent.setSystemInfo("Framework", "Selenium + TestNG + Maven");
        }
        return extent;
    }

    public static void createTest(String testName) {
        ExtentTest extentTest = getInstance().createTest(testName);
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
