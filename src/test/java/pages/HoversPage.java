package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseActions;

import java.util.List;

/**
 * Hovers Page
 */
public class HoversPage extends BaseActions {
    
    private final By figures = By.cssSelector(".figure");
    private final By captions = By.cssSelector(".figcaption");
    
    public HoversPage(WebDriver driver) {
        super(driver);
    }
    
    public HoversPage navigateTo() {
        navigateTo("https://the-internet.herokuapp.com/hovers");
        return this;
    }
    
    public void hoverOverFigure(int index) {
        List<WebElement> allFigures = findAll(figures);
        if (index < allFigures.size()) {
            actions.moveToElement(allFigures.get(index)).perform();
        }
    }
    
    public String getCaptionText(int index) {
        List<WebElement> allCaptions = findAll(captions);
        if (index < allCaptions.size()) {
            return allCaptions.get(index).getText();
        }
        return "";
    }
    
    public boolean isCaptionDisplayed(int index) {
        List<WebElement> allCaptions = findAll(captions);
        if (index < allCaptions.size()) {
            return allCaptions.get(index).isDisplayed();
        }
        return false;
    }
    
    public int getFigureCount() {
        return findAll(figures).size();
    }
}