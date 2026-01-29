package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseActions;

import java.util.List;

/**
 * Disappearing Elements Page - Elements that appear/disappear on refresh
 */
public class DisappearingElementsPage extends BaseActions {
    
    private final By menuItems = By.cssSelector(".example li");
    private final By homeLink = By.linkText("Home");
    private final By aboutLink = By.linkText("About");
    private final By contactUsLink = By.linkText("Contact Us");
    private final By portfolioLink = By.linkText("Portfolio");
    private final By galleryLink = By.linkText("Gallery");
    
    public DisappearingElementsPage(WebDriver driver) {
        super(driver);
    }
    
    public DisappearingElementsPage navigateTo() {
        navigateTo("https://the-internet.herokuapp.com/disappearing_elements");
        return this;
    }
    
    public int getMenuItemCount() {
        return findAll(menuItems).size();
    }
    
    public boolean isGalleryVisible() {
        return isDisplayed(galleryLink);
    }
    
    public boolean isPortfolioVisible() {
        return isDisplayed(portfolioLink);
    }
    
    public void refreshUntilGalleryAppears(int maxAttempts) {
        for (int i = 0; i < maxAttempts; i++) {
            if (isGalleryVisible()) break;
            driver.navigate().refresh();
        }
    }
}