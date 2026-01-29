package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseActions;
import utils.Constants;

/**
 * Refactored Login Page using Constants
 */
public class FormAuthenticationPage extends BaseActions {
    
    // Locators
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By flashMessage = By.id("flash");
    private final By logoutButton = By.cssSelector("a[href='/logout']");
    private final By secureAreaHeader = By.cssSelector("h2");
    
    public FormAuthenticationPage(WebDriver driver) {
        super(driver);
    }
    
    public FormAuthenticationPage navigateTo() {
        navigateTo(Constants.Urls.FORM_AUTHENTICATION);
        waitForPageLoad();
        return this;
    }
    
    public FormAuthenticationPage enterUsername(String username) {
        type(usernameField, username);
        return this;
    }
    
    public FormAuthenticationPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }
    
    public FormAuthenticationPage clickLogin() {
        safeClick(loginButton);
        return this;
    }
    
    /**
     * Fluent API for login
     */
    public FormAuthenticationPage login(String username, String password) {
        return enterUsername(username)
               .enterPassword(password)
               .clickLogin();
    }
    
    /**
     * Login with valid credentials (using constants)
     */
    public FormAuthenticationPage loginWithValidCredentials() {
        return login(Constants.Credentials.VALID_USERNAME, 
                     Constants.Credentials.VALID_PASSWORD);
    }
    
    /**
     * Login with invalid credentials (using constants)
     */
    public FormAuthenticationPage loginWithInvalidCredentials() {
        return login(Constants.Credentials.INVALID_USERNAME, 
                     Constants.Credentials.INVALID_PASSWORD);
    }
    
    public String getFlashMessage() {
        return waitForVisible(flashMessage).getText().trim();
    }
    
    public boolean isFlashMessageDisplayed() {
        return isDisplayed(flashMessage);
    }
    
    public boolean isFlashMessageContains(String text) {
        return getFlashMessage().contains(text);
    }
    
    public void clickLogout() {
        safeClick(logoutButton);
    }
    
    public boolean isLogoutButtonDisplayed() {
        return isDisplayed(logoutButton);
    }
    
    public boolean isSecureAreaDisplayed() {
        return isDisplayed(secureAreaHeader) && 
               getText(secureAreaHeader).contains("Secure Area");
    }
    
    public boolean isOnLoginPage() {
        return getCurrentUrl().contains("/login");
    }
}