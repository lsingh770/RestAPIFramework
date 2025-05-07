package com.api.baseclass;

import com.api.utils.ReportLabelUpdater;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;

import java.lang.reflect.Method;

public class BaseTest {
    protected static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();

    @BeforeSuite
    public void setup() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.setSystemInfo("<b>Note</b>", "<i>Modules means the classes that are executed in this run. Method-level tests are shown as Nodes.</i>");
        extent.attachReporter(spark);
    }

    @BeforeClass
    public void setupModuleNode() {
        String moduleName = this.getClass().getSimpleName();  // e.g., GetTests
        ExtentTest parent = extent.createTest(moduleName);
        parentTest.set(parent);
    }

    @BeforeMethod
    public void startTest(Method method) {
        String testName = method.getName(); // e.g., testGetUser
        ExtentTest child = parentTest.get().createNode(testName);
        test.set(child);
    }

    @AfterMethod
    public void logTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger().fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger().skip("Test Skipped");
        } else {
            logger().pass("Test Passed");
        }
    }


    @AfterSuite
    public void tearDownExtent() {
        extent.flush();
        ReportLabelUpdater.updateExtentReportLabels("target/ExtentReport.html");
    }

    public ExtentTest logger() {
        return test.get();
    }
}
