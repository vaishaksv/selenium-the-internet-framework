package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retry Analyzer - Automatically retries failed tests
 * Add to test: @Test(retryAnalyzer = RetryAnalyzer.class)
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    
    private static final int MAX_RETRY_COUNT = 2;
    private ThreadLocal<Integer> retryCount = ThreadLocal.withInitial(() -> 0);
    
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount.get() < MAX_RETRY_COUNT) {
            retryCount.set(retryCount.get() + 1);
            LoggerUtil.warn("Retrying test: " + result.getName() + 
                           " | Attempt: " + (retryCount.get() + 1));
            return true;
        }
        retryCount.set(0); // Reset for next test
        return false;
    }
    
    public static int getMaxRetries() {
        return MAX_RETRY_COUNT;
    }
}