package com.parabank.listeners;

import com.aventstack.extentreports.Status;
import com.parabank.driver.DriverFactory;
import com.parabank.utilities.ExtentReportManager;
import com.parabank.utilities.ScreenshotUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Hooks into TestNG's test lifecycle. Registered via testng.xml <listeners> tag.
 * Responsible for:
 * - creating an ExtentTest entry per test method
 * - logging pass/fail status to the HTML report
 * - capturing a screenshot automatically on failure and attaching it to the report
 */
public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.createTest(result.getMethod().getMethodName());
        logger.info("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test passed");
        logger.info("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.error("Test failed: " + testName, result.getThrowable());

        String screenshotPath = ScreenshotUtility.captureScreenshot(DriverFactory.getDriver(), testName);
        ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + result.getThrowable());
        ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "Test skipped: " + result.getThrowable());
        logger.warn("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.getInstance().flush();
        logger.info("Test suite finished. Report generated at reports/ExtentReport.html");
    }
}
