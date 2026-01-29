package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Enhanced Test Listener with detailed reporting
 */
public class TestListener implements ITestListener {
    
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    
    @Override
    public void onStart(ITestContext context) {
        LoggerUtil.info("══════════════════════════════════════════════════════════");
        LoggerUtil.info("  SUITE: " + context.getName());
        LoggerUtil.info("  Browser: " + System.getProperty("browser", "chrome"));
        LoggerUtil.info("  Thread Count: " + context.getSuite().getXmlSuite().getThreadCount());
        LoggerUtil.info("══════════════════════════════════════════════════════════");
    }
    
    @Override
    public void onFinish(ITestContext context) {
        int total = context.getAllTestMethods().length;
        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();
        
        LoggerUtil.info("══════════════════════════════════════════════════════════");
        LoggerUtil.info("  TEST RESULTS");
        LoggerUtil.info("  Total:  " + total);
        LoggerUtil.info("  Passed: " + passed + " ✓");
        LoggerUtil.info("  Failed: " + failed + " ✗");
        LoggerUtil.info("  Skipped: " + skipped + " ⚠");
        LoggerUtil.info("══════════════════════════════════════════════════════════");
        
        if (failed > 0) {
            System.out.println(RED + "SOME TESTS FAILED!" + RESET);
        } else {
            System.out.println(GREEN + "ALL TESTS PASSED!" + RESET);
        }
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtil.info("──────────────────────────────────────────────────────────");
        LoggerUtil.info("▶ STARTING: " + result.getName());
        if (result.getMethod().getDescription() != null) {
            LoggerUtil.info("  Description: " + result.getMethod().getDescription());
        }
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        long duration = result.getEndMillis() - result.getStartMillis();
        LoggerUtil.info(GREEN + "✓ PASSED: " + result.getName() + " (" + duration + "ms)" + RESET);
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        long duration = result.getEndMillis() - result.getStartMillis();
        LoggerUtil.error(RED + "✗ FAILED: " + result.getName() + " (" + duration + "ms)" + RESET);
        
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            LoggerUtil.error("  Error: " + throwable.getMessage());
            
            // Capture screenshot
            Object driver = result.getAttribute("driver");
            if (driver != null) {
                String screenshotPath = ScreenshotHelper.takeScreenshot(
                    (org.openqa.selenium.WebDriver) driver, 
                    result.getName()
                );
                if (screenshotPath != null) {
                    LoggerUtil.info("  Screenshot: " + screenshotPath);
                }
            }
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtil.warn(YELLOW + "⚠ SKIPPED: " + result.getName() + RESET);
    }
}