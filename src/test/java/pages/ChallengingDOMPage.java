package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseActions;

import java.util.List;

/**
 * Challenging DOM Page - Dynamic elements with changing IDs
 */
public class ChallengingDOMPage extends BaseActions {
    
    // These buttons have dynamic IDs, use CSS selectors
    private final By blueButton = By.cssSelector(".button");
    private final By redButton = By.cssSelector(".button.alert");
    private final By greenButton = By.cssSelector(".button.success");
    
    // Canvas for answer verification
    private final By canvas = By.id("canvas");
    
    // Table
    private final By tableRows = By.cssSelector("table tbody tr");
    
    public ChallengingDOMPage(WebDriver driver) {
        super(driver);
    }
    
    public ChallengingDOMPage navigateTo() {
        navigateTo("https://the-internet.herokuapp.com/challenging_dom");
        return this;
    }
    
    public void clickBlueButton() {
        click(blueButton);
    }
    
    public void clickRedButton() {
        click(redButton);
    }
    
    public void clickGreenButton() {
        click(greenButton);
    }
    
    public boolean isCanvasDisplayed() {
        return isDisplayed(canvas);
    }
    
    public int getTableRowCount() {
        return findAll(tableRows).size();
    }
    
    public String getTableCell(int row, int col) {
        By cell = By.cssSelector("table tbody tr:nth-child(" + row + ") td:nth-child(" + col + ")");
        return getText(cell);
    }
}