package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseActions;

/**
 * Infinite Scroll Page
 */
public class InfiniteScrollPage extends BaseActions {
    
    private final By paragraphs = By.cssSelector(".jscroll-added");
    
    public InfiniteScrollPage(WebDriver driver) {
        super(driver);
    }
    
    public InfiniteScrollPage navigateTo() {
        navigateTo("https://the-internet.herokuapp.com/infinite_scroll");
        return this;
    }
    
    public void scrollToBottom() {
        scrollToBottom();
    }
    
    public int getParagraphCount() {
        return findAll(paragraphs).size();
    }
    
    public void scrollMultipleTimes(int times) {
        for (int i = 0; i < times; i++) {
            scrollToBottom();
            waitSeconds(2); // Wait for content to load
        }
    }
}