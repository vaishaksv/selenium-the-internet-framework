package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utils.BaseActions;
import utils.Constants;

/**
 * Refactored Key Presses Page
 */
public class KeyPressesPage extends BaseActions {
    
    private final By target = By.id("target");
    private final By result = By.id("result");
    
    public KeyPressesPage(WebDriver driver) {
        super(driver);
    }
    
    public KeyPressesPage navigateTo() {
        navigateTo(Constants.Urls.KEY_PRESSES);
        return this;
    }
    
    public void pressKey(Keys key) {
        find(target).sendKeys(key);
    }
    
    public String getResultText() {
        return getText(result);
    }
}