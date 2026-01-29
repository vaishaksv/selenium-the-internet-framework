package tests;

import org.testng.annotations.Test;
import pages.FormAuthenticationPage;
import utils.*;

/**
 * Data Driven Tests using DataProviderFactory
 */
public class DataDrivenTests extends BaseTest {
    
    @Test(dataProvider = "loginDataProvider", 
          dataProviderClass = DataProviderFactory.class,
          description = "DDT: Login with various credentials",
          groups = {"regression", "data-driven"})
    public void testLoginWithDataProvider(String username, String password, 
                                          boolean shouldSucceed, String description) {
        
        AllureHelper.logStep("Testing: " + description);
        
        // Use TestDataBuilder
        TestDataBuilder.TestData testData = new TestDataBuilder()
            .withUsername(username)
            .withPassword(password)
            .build();
        
        FormAuthenticationPage page = new FormAuthenticationPage(driver);
        page.navigateTo()
            .login(testData.username, testData.password);
        
        // Soft assertions for multiple validations
        AssertionHelper.initSoftAssert();
        
        AssertionHelper.softAssertEquals(
            page.isSecureAreaDisplayed(), 
            shouldSucceed, 
            "Secure area visibility should match expected"
        );
        
        AssertionHelper.softAssertEquals(
            page.isFlashMessageDisplayed(), 
            true, 
            "Flash message should always be displayed"
        );
        
        if (shouldSucceed) {
            AssertionHelper.softAssertTrue(
                page.isFlashMessageContains(Constants.Messages.LOGIN_SUCCESS),
                "Success message should be displayed"
            );
        }
        
        // Assert all soft assertions
        AssertionHelper.assertAll();
        
        AllureHelper.logStep("Test completed: " + description);
    }
    
    @Test(dataProvider = "dynamicProvider",
          dataProviderClass = DataProviderFactory.class,
          description = "DDT: Dynamic key presses",
          groups = {"regression"})
    public void testDynamicKeyPresses(org.openqa.selenium.Keys key, String expectedText) {
        AllureHelper.logStep("Testing key: " + key.name());
        
        // Use PageFactory for cleaner code
        var page = new PageFactory(driver).getKeyPressesPage();
        
        page.pressKey(key);
        
        String result = page.getResultText();
        AssertionHelper.assertTrue(
            result.contains(expectedText),
            "Result should contain: " + expectedText + " but was: " + result
        );
    }
}