package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseActions;

/**
 * Checkboxes Page
 */
public class CheckboxesPage extends BaseActions {
    
    private final By checkbox1 = By.xpath("//input[1]");
    private final By checkbox2 = By.xpath("//input[2]");
    private final By allCheckboxes = By.cssSelector("input[type='checkbox']");
    
    public CheckboxesPage(WebDriver driver) {
        super(driver);
    }
    
    public CheckboxesPage navigateTo() {
        navigateTo("https://the-internet.herokuapp.com/checkboxes");
        return this;
    }
    
    public void checkCheckbox1() {
        check(checkbox1);
    }
    
    public void uncheckCheckbox1() {
        uncheck(checkbox1);
    }
    
    public void checkCheckbox2() {
        check(checkbox2);
    }
    
    public void uncheckCheckbox2() {
        uncheck(checkbox2);
    }
    
    public boolean isCheckbox1Checked() {
        return isSelected(checkbox1);
    }
    
    public boolean isCheckbox2Checked() {
        return isSelected(checkbox2);
    }
    
    public int getCheckboxCount() {
        return findAll(allCheckboxes).size();
    }
}