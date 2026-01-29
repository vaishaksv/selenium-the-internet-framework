package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseActions;

/**
 * Multiple Windows Page
 */
public class MultipleWindowsPage extends BaseActions {
    
    private final By clickHereLink = By.linkText("Click Here");
    
    // New window elements
    private final By newWindowHeading = By.tagName("h3");
    
    public MultipleWindowsPage(WebDriver driver) {
        super(driver);
    }
    
    public MultipleWindowsPage navigateTo() {
        navigateTo("https://the-internet.herokuapp.com/windows");
        return this;
    }
    
    public void clickClickHere() {
        click(clickHereLink);
    }
    
    public void switchToNewWindow() {
        switchToNewWindow();
    }
    
    public String getNewWindowText() {
        return getText(newWindowHeading);
    }
    
    public void closeNewWindowAndSwitchBack() {
        closeWindow();
        String mainWindow = getAllWindows().iterator().next();
        switchToWindow(mainWindow);
    }
}