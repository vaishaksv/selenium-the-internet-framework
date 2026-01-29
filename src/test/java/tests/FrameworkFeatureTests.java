package tests;

import org.testng.annotations.Test;
import utils.*;

/**
 * Demonstrates all new framework features
 */
public class FrameworkFeatureTests extends BaseTest {
    
    @Test(description = "Feature Demo: Test Data Builder")
    public void testDataBuilderDemo() {
        // Create test data using builder pattern
        TestDataBuilder.TestData validUser = TestDataBuilder.validUser();
        TestDataBuilder.TestData customUser = new TestDataBuilder()
            .withUsername("customuser")
            .withPassword("custompass")
            .withEmail("custom@test.com")
            .withFirstName("John")
            .withLastName("Doe")
            .build();
        
        LoggerUtil.info("Valid user: " + validUser);
        LoggerUtil.info("Custom user: " + customUser);
        
        // Use the data
        AssertionHelper.assertNotNull(validUser.username, "Username should not be null");
        AssertionHelper.assertNotNull(customUser.firstName, "First name should not be null");
    }
    
    @Test(description = "Feature Demo: Environment Manager")
    public void testEnvironmentManager() {
        EnvironmentManager.Environment env = EnvironmentManager.getCurrentEnvironment();
        String baseUrl = EnvironmentManager.getBaseUrl();
        
        LoggerUtil.info("Current environment: " + env);
        LoggerUtil.info("Base URL: " + baseUrl);
        
        EnvironmentManager.Config config = EnvironmentManager.getConfig();
        LoggerUtil.info("Retry count: " + config.getRetryCount());
        LoggerUtil.info("Should capture video: " + config.shouldCaptureVideo());
        
        AssertionHelper.assertNotNull(env, "Environment should not be null");
        AssertionHelper.assertNotNull(baseUrl, "Base URL should not be null");
    }
    
    @Test(description = "Feature Demo: Page Factory")
    public void testPageFactoryDemo() {
        // Use PageFactory for cleaner test code
        PageFactory factory = new PageFactory(driver);
        
        var loginPage = factory.getFormAuthenticationPage();
        AssertionHelper.assertTrue(loginPage.isOnLoginPage(), "Should be on login page");
        
        var checkboxesPage = factory.getCheckboxesPage();
        AssertionHelper.assertTrue(checkboxesPage.isCheckbox1Checked() || !checkboxesPage.isCheckbox1Checked(), 
            "Checkbox state should be readable");
    }
    
    @Test(description = "Feature Demo: Soft Assertions",
          groups = {"regression"})
    public void testSoftAssertions() {
        // Multiple assertions, all run even if one fails
        AssertionHelper.initSoftAssert();
        
        AssertionHelper.softAssertTrue(true, "This should pass");
        AssertionHelper.softAssertEquals("actual", "actual", "Strings should match");
        AssertionHelper.softAssertNotNull("not null", "Should not be null");
        
        // This will collect all failures and report at the end
        AssertionHelper.assertAll();
    }
    
    @Test(description = "Feature Demo: Allure Reporting")
    public void testAllureFeatures() {
        // Set Allure metadata
        AllureHelper.setFeature("Framework Features");
        AllureHelper.setStory("Allure Integration");
        AllureHelper.setSeverity(io.qameta.allure.SeverityLevel.NORMAL);
        AllureHelper.setDescription("This test demonstrates Allure reporting capabilities");
        
        // Log steps
        AllureHelper.logStep("Step 1: Navigate to application");
        driver.get(Constants.BASE_URL);
        
        AllureHelper.logStep("Step 2: Verify page loaded");
        AssertionHelper.assertEquals(driver.getTitle(), "The Internet", "Title should match");
        
        AllureHelper.logStep("Step 3: Attach screenshot");
        AllureHelper.attachScreenshot(driver);
        
        AllureHelper.logStep("Step 4: Attach additional info");
        AllureHelper.attachText("Environment", EnvironmentManager.getCurrentEnvironment().name());
        AllureHelper.attachText("Browser", System.getProperty("browser", "chrome"));
    }
}