package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import utils.*;

/**
 * Home Page Tests with Allure Reporting
 */
public class HomePageTests extends BaseTest {
    
    @Test(description = "Verify home page loads correctly",
          groups = {"smoke"})
    public void testHomePageLoads() {
        // Allure reporting
        AllureHelper.setFeature("Navigation");
        AllureHelper.setStory("Home Page");
        AllureHelper.setSeverity(io.qameta.allure.SeverityLevel.CRITICAL);
        
        AllureHelper.logStep("Navigate to home page");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHome();
        
        AllureHelper.logStep("Verify page title");
        AssertionHelper.assertEquals(
            homePage.getPageTitle(),
            "The Internet",
            "Page title should be 'The Internet'"
        );
        
        AllureHelper.logStep("Verify heading");
        AssertionHelper.assertEquals(
            homePage.getHeading(),
            "Welcome to the-internet",
            "Heading should match"
        );
        
        // Attach screenshot
        AllureHelper.attachScreenshot(driver);
    }
    
    @Test(description = "Verify all page links are present",
          groups = {"regression"})
    public void testAllLinksPresent() {
        AllureHelper.setFeature("Navigation");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHome();
        
        // Use soft assertions for multiple checks
        AssertionHelper.initSoftAssert();
        
        // Verify at least some links exist
        AssertionHelper.softAssertTrue(
            driver.getPageSource().contains("Add/Remove Elements"),
            "Add/Remove Elements link should exist"
        );
        
        AssertionHelper.softAssertTrue(
            driver.getPageSource().contains("Form Authentication"),
            "Form Authentication link should exist"
        );
        
        AssertionHelper.softAssertTrue(
            driver.getPageSource().contains("JavaScript Alerts"),
            "JavaScript Alerts link should exist"
        );
        
        AssertionHelper.assertAll();
    }
}