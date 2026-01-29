package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseActions;

import java.util.List;

/**
 * Add/Remove Elements Page
 */
public class AddRemoveElementsPage extends BaseActions {
    
    private final By addElementButton = By.cssSelector("button[onclick='addElement()']");
    private final By deleteButtons = By.cssSelector("button.added-manually");
    
    public AddRemoveElementsPage(WebDriver driver) {
        super(driver);
    }
    
    public AddRemoveElementsPage navigateTo() {
        navigateTo("https://the-internet.herokuapp.com/add_remove_elements/");
        return this;
    }
    
    public void clickAddElement() {
        click(addElementButton);
    }
    
    public void addElements(int count) {
        for (int i = 0; i < count; i++) {
            clickAddElement();
        }
    }
    
    public void clickDelete(int index) {
        List<WebElement> buttons = findAll(deleteButtons);
        if (index < buttons.size()) {
            buttons.get(index).click();
        }
    }
    
    public void deleteAll() {
        List<WebElement> buttons = findAll(deleteButtons);
        while (!buttons.isEmpty()) {
            buttons.get(0).click();
            buttons = findAll(deleteButtons);
        }
    }
    
    public int getDeleteButtonCount() {
        return findAll(deleteButtons).size();
    }
    
    public boolean isDeleteButtonPresent() {
        return isDisplayed(deleteButtons);
    }
}