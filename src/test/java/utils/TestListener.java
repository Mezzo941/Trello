package utils;

import factory.ScreenshotFactory;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println();
        System.out.printf("======================================== STARTING TEST %s ========================================%n", iTestResult.getName());
    }

    @SneakyThrows
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        ScreenshotFactory.createScreenshot(driver, iTestResult);
        System.out.printf("======================================== FINISHED TEST %s Duration: %ss ========================================%n", iTestResult.getName(),
                getExecutionTime(iTestResult));
        System.out.println();
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        ScreenshotFactory.createScreenshot(driver, iTestResult);
        System.out.printf("======================================== FAILED TEST %s Duration: %ss ========================================%n", iTestResult.getName(),
                getExecutionTime(iTestResult));
        System.out.println();
    }

    @SneakyThrows
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        ScreenshotFactory.createScreenshot(driver, iTestResult);
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