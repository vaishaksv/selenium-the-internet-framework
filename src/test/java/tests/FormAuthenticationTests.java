package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FormAuthenticationPage;
import utils.Constants;

/**
 * Refactored Login Tests with better assertions
 */
public class FormAuthenticationTests extends BaseTest {
    
    @Test(description = "TC001: Verify successful login with valid credentials")
    public void testValidLogin() {
        FormAuthenticationPage page = new FormAuthenticationPage(driver);
        page.navigateTo()
            .loginWithValidCredentials();
        
        // Verify success
        Assert.assertTrue(page.isSecureAreaDisplayed(), 
            "User should be on secure area after successful login");
        Assert.assertTrue(page.isFlashMessageContains(Constants.Messages.LOGIN_SUCCESS), 
            "Success message should be displayed");
        Assert.assertTrue(page.isLogoutButtonDisplayed(), 
            "Logout button should be visible");
    }
    
    @Test(description = "TC002: Verify login fails with invalid username")
    public void testInvalidUsername() {
        FormAuthenticationPage page = new FormAuthenticationPage(driver);
        page.navigateTo()
            .enterUsername(Constants.Credentials.INVALID_USERNAME)
            .enterPassword(Constants.Credentials.VALID_PASSWORD)
            .clickLogin();
        
        Assert.assertTrue(page.isOnLoginPage(), 
            "User should remain on login page");
        Assert.assertTrue(page.isFlashMessageContains(Constants.Messages.LOGIN_INVALID_USER), 
            "Invalid username error should be displayed");
    }
    
    @Test(description = "TC003: Verify login fails with invalid password")
    public void testInvalidPassword() {
        FormAuthenticationPage page = new FormAuthenticationPage(driver);
        page.navigateTo()
            .enterUsername(Constants.Credentials.VALID_USERNAME)
            .enterPassword(Constants.Credentials.INVALID_PASSWORD)
            .clickLogin();
        
        Assert.assertTrue(page.isOnLoginPage(), 
            "User should remain on login page");
        Assert.assertTrue(page.isFlashMessageContains(Constants.Messages.LOGIN_INVALID_PASS), 
            "Invalid password error should be displayed");
    }
    
    @Test(description = "TC004: Verify login fails with empty credentials")
    public void testEmptyCredentials() {
        FormAuthenticationPage page = newAuthenticationPage(driver);
        page.navigateTo()
            .enterUsername("")
            .enterPassword("")
            .clickLogin();
        
        Assert.assertTrue(page.isOnLoginPage(), 
            "User should remain on login page");
        Assert.assertTrue(page.isFlashMessageDisplayed(), 
            "Error message should be displayed");
    }
    
    @Test(description = "TC005: Verify logout functionality")
    public void testLogout() {
        FormAuthenticationPage page = new FormAuthenticationPage(driver);
        
        // Login first
        page.navigateTo()
            .loginWithValidCredentials();
        
        Assert.assertTrue(page.isLogoutButtonDisplayed(), 
            "Should be logged in");
        
        // Logout
        page.clickLogout();
        
        Assert.assertTrue(page.isOnLoginPage(), 
            "Should be back on login page");
        Assert.assertTrue(page.isFlashMessageContains(Constants.Messages.LOGOUT_SUCCESS), 
            "Logout message should be displayed");
    }
    
    private FormAuthenticationPage newAuthenticationPage(WebDriver driver) {
        return new FormAuthenticationPage(driver);
    }
}