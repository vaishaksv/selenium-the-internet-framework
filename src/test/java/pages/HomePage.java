package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseActions;
import utils.Constants;

/**
 * Refactored Home Page
 */
public class HomePage extends BaseActions {
    
    private final By heading = By.tagName("h1");
    private final By subheading = By.tagName("h2");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public HomePage navigateToHome() {
        navigateTo(Constants.BASE_URL);
        waitForPageLoad();
        return this;
    }
    
    public String getHeading() {
        return getText(heading);
    }
    
    public String getSubheading() {
        return getText(subheading);
    }
}