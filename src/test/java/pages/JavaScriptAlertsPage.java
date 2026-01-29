package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseActions;
import utils.Constants;

/**
 * Refactored JavaScript Alerts Page
 */
public class JavaScriptAlertsPage extends BaseActions {
    
    private final By jsAlertButton = By.xpath("//button[text()='Click for JS Alert']");
    private final By jsConfirmButton = By.xpath("//button[text()='Click for JS Confirm']");
    private final By jsPromptButton = By.xpath("//button[text()='Click for JS Prompt']");
    private final By resultText = By.id("result");
    
    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
    }
    
    public JavaScriptAlertsPage navigateTo() {
        navigateTo(Constants.Urls.JAVASCRIPT_ALERTS);
        return this;
    }
    
    // Alert methods
    public JavaScriptAlertsPage triggerAlert() {
        click(jsAlertButton);
        return this;
    }
    
    // Confirm methods
    public JavaScriptAlertsPage triggerConfirm() {
        click(jsConfirmButton);
        return this;
    }
    
    // Prompt methods
    public JavaScriptAlertsPage triggerPrompt() {
        click(jsPromptButton);
        return this;
    }
    
    public String getResultText() {
        return waitForVisible(resultText).getText();
    }
}