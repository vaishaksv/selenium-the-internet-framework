package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseActions;

/**
 * Dropdown Page
 */
public class DropdownPage extends BaseActions {
    
    private final By dropdown = By.id("dropdown");
    
    public DropdownPage(WebDriver driver) {
        super(driver);
    }
    
    public DropdownPage navigateTo() {
        navigateTo("https://the-internet.herokuapp.com/dropdown");
        return this;
    }
    
    public void selectOption1() {
        selectByIndex(dropdown, 1);
    }
    
    public void selectOption2() {
        selectByIndex(dropdown, 2);
    }
    
    public void selectByText(String text) {
        selectByText(dropdown, text);
    }
    
    public void selectByValue(String value) {
        selectByValue(dropdown, value);
    }
    
    public String getSelectedOption() {
        return getSelectedOption(dropdown);
    }
}