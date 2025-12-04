package org.practice.utilsForTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.practice.base.BasePage;
import org.practice.base.BaseTest;
import org.practice.base.Page;
import org.practice.utilities.ReporterUtility;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener {
    ExtentReports extent = ReporterUtility.generateReport();
    ExtentTest test;
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    WebDriver driver;
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName(), "Started generating report for : " + result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "Test is passed for testCase : " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().log(Status.FAIL, "Test is failed for testCase : " + result.getMethod().getMethodName());
        testThread.get().fail(result.getThrowable());
        try {
            // This gets the WebDriver field reflectively from your test class
            driver = (WebDriver) result.getInstance().getClass().
                    getSuperclass().getDeclaredField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(driver!=null){
            try {
                String filepath = Page.getScreenshot(result.getMethod().getMethodName(), driver);
                test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Driver is null, cannot capture screenshot!");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Test is skipped for testCase : " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Started automation test run" + context.getStartDate());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}

