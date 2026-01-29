package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.*;

/**
 * Refactored Base Test with improved setup/teardown
 */
public class BaseTest {
    
    protected WebDriver driver;
    protected BaseActions actions;
    
    @BeforeSuite
    public void beforeSuite() {
        LoggerUtil.info("╔══════════════════════════════════════════════════════════╗");
        LoggerUtil.info("║     THE INTERNET - SELENIUM AUTOMATION FRAMEWORK         ║");
        LoggerUtil.info("╚══════════════════════════════════════════════════════════╝");
    }
    
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "headless"})
    public void setUp(@Optional("chrome") String browser, 
                      @Optional("false") String headless,
                      ITestResult result) {
        
        // System property overrides parameter
        String browserName = System.getProperty("browser", browser);
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", headless));
        
        LoggerUtil.startTest(result.getName());
        
        driver = DriverManager.initializeDriver(browserName, isHeadless);
        actions = new BaseActions(driver);
        
        // Store driver in result for listener access
        result.setAttribute("driver", driver);
    }
    
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        DriverManager.quitDriver();
        
        String status = result.getStatus() == ITestResult.SUCCESS ? "PASSED" :
                       result.getStatus() == ITestResult.FAILURE ? "FAILED" : "SKIPPED";
        LoggerUtil.endTest(result.getName(), status);
    }
    
    @AfterSuite
    public void afterSuite() {
        LoggerUtil.info("Test execution completed");
    }
    
    // Helper methods for tests
    protected void navigateToHome() {
        driver.get(Constants.BASE_URL);
    }
    
    protected void navigateTo(String url) {
        driver.get(url);
    }
    
    protected void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}