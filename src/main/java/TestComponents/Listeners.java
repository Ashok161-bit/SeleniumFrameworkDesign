package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReportsNg;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReportsNg.getReporterObject();
    public WebDriver driver;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        if (extent == null) {
            System.out.println("ExtentReports object is null. Ensure it is initialized properly.");
            return;
        }
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        System.out.println("Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
        System.out.println("Test Passed: " + result.getMethod().getMethodName());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            System.out.println(driver);
        } catch (Exception e) {
            System.out.println("Error retrieving WebDriver instance:");
            e.printStackTrace();
        }

        try {
            String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
            extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        } catch (IOException e) {
            System.out.println("Error capturing screenshot:");
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getMethod().getMethodName());
        extentTest.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            System.out.println(driver);
        } catch (Exception e) {
            System.out.println("Error retrieving WebDriver instance:");
            e.printStackTrace();
        }

        try {
            String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
            extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        } catch (IOException e) {
            System.out.println("Error capturing screenshot:");
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional: Implement if needed
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test context started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
        System.out.println("Test context finished: " + context.getName());
    }
}
