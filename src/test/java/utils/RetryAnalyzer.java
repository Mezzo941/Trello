package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int testTry = 0;
    private static final int LAST_TEST_TRY = 1;

    @Override
    public boolean retry(ITestResult result) {

        if (result.isSuccess()) {
            result.setStatus(ITestResult.SUCCESS);
        } else {
            if (testTry < LAST_TEST_TRY) {
                testTry++;
                result.setStatus(ITestResult.FAILURE);
                return true;
            } else {
                result.setStatus(ITestResult.FAILURE);
            }
        }
        return false;
    }
}
