package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseActions;

import java.util.List;

/**
 * Dynamic Loading Page - Elements that appear after loading
 */
public class DynamicLoadingPage extends BaseActions {
    
    private final By example1Link = By.linkText("Example 1: Element on page that is hidden");
    private final By example2Link = By.linkText("Example 2: Element rendered after the fact");
    
    // Example 1 elements
    private final By startButton = By.cssSelector("#start button");
    private final By loadingIndicator = By.id("loading");
    private final By finishText = By.id("finish");
    
    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
    }
    
    public DynamicLoadingPage navigateTo() {
        navigateTo("https://the-internet.herokuapp.com/dynamic_loading");
        return this;
    }
    
    public void clickExample1() {
        click(example1Link);
    }
    
    public void clickExample2() {
        click(example2Link);
    }
    
    public void clickStart() {
        click(startButton);
    }
    
    public void waitForLoadingToComplete() {
        waitForInvisibility(loadingIndicator);
    }
    
    public String getFinishText() {
        return getText(finishText);
    }
    
    public boolean isFinishTextDisplayed() {
        return isDisplayed(finishText);
    }
}