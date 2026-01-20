package org.practice.utilsForTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.practice.DriverFactory.DriverManager;
import org.practice.base.Page;
import org.practice.utilities.ExtentLoggerUtil;
import org.practice.utilities.ReporterUtility;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
    ExtentReports extent = ReporterUtility.generateReport();
    ExtentTest test;


    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName(), "Started generating report for : " + result.getMethod().getMethodName());
        ExtentLoggerUtil.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLoggerUtil.pass("Test is passed for testCase : : " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentLoggerUtil.fail("Test failed : " + result.getMethod().getMethodName());
        ExtentLoggerUtil.fail(result.getThrowable().getMessage());

        if(DriverManager.getDriver() != null){
            String filepath = Page.captureScreenshot(result.getMethod().getMethodName());
            ExtentLoggerUtil.getTest().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLoggerUtil.warn("Test skipped : " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        extent.setSystemInfo("Suite", context.getSuite().getName());
        extent.setSystemInfo("Start Time", context.getStartDate().toString());

    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentLoggerUtil.unload();
        extent.flush();
    }
}

