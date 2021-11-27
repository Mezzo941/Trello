package utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        System.out.println();
        System.out.printf("======================================== STARTING TEST %s ========================================%n", iTestResult.getName());
        log.info("driver details: " + driver);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        ScreenshotUtils.createScreenshot(driver, iTestResult.getTestClass().getName(), iTestResult);
        System.out.printf("======================================== TEST %s PASSED Duration: %ss ========================================%n", iTestResult.getName(),
                getExecutionTime(iTestResult));
        System.out.println();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        ScreenshotUtils.createScreenshot(driver, iTestResult.getTestClass().getName(), iTestResult);
        System.out.printf("======================================== FAILED TEST %s Duration: %ss ========================================%n", iTestResult.getName(),
                getExecutionTime(iTestResult));
        System.out.println();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        ScreenshotUtils.createScreenshot(driver, iTestResult.getTestClass().getName(), iTestResult);
        System.out.printf("======================================== SKIPPING TEST %s ========================================%n", iTestResult.getName());
        System.out.println();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }


}