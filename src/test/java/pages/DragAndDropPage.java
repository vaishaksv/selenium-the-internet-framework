package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseActions;

import java.util.List;

/**
 * Drag and Drop Page
 */
public class DragAndDropPage extends BaseActions {
    
    private final By columnA = By.id("column-a");
    private final By columnB = By.id("column-b");
    private final By columnAHeader = By.cssSelector("#column-a header");
    private final By columnBHeader = By.cssSelector("#column-b header");
    
    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }
    
    public DragAndDropPage navigateTo() {
        navigateTo("https://the-internet.herokuapp.com/drag_and_drop");
        return this;
    }
    
    public void dragAtoB() {
        dragAndDrop(columnA, columnB);
    }
    
    public void dragBtoA() {
        dragAndDrop(columnB, columnA);
    }
    
    public String getColumnAText() {
        return getText(columnAHeader);
    }
    
    public String getColumnBText() {
        return getText(columnBHeader);
    }
    
    // Alternative using JavaScript for browsers where HTML5 drag-drop doesn't work
    public void dragAtoBUsingJS() {
        String script = "function createEvent(typeOfEvent) {" +
            "var event = document.createEvent('CustomEvent');" +
            "event.initCustomEvent(typeOfEvent, true, true, null);" +
            "event.dataTransfer = { data: {}, setData: function(key, value) { this.data[key] = value; }, getData: function(key) { return this.data[key]; } };" +
            "return event; }" +
            "function dispatchEvent(element, event, transferData) {" +
            "if (transferData !== undefined) { event.dataTransfer = transferData; }" +
            "if (element.dispatchEvent) { element.dispatchEvent(event); } }" +
            "function simulateHTML5DragAndDrop(element, destination) {" +
            "var dragStartEvent = createEvent('dragstart');" +
            "dispatchEvent(element, dragStartEvent);" +
            "var dropEvent = createEvent('drop');" +
            "dispatchEvent(destination, dropEvent, dragStartEvent.dataTransfer);" +
            "var dragEndEvent = createEvent('dragend');" +
            "dispatchEvent(element, dragEndEvent, dropEvent.dataTransfer); }" +
            "var source = arguments[0]; var destination = arguments[1];" +
            "simulateHTML5DragAndDrop(source, destination);";
        
        WebElement source = find(columnA);
        WebElement target = find(columnB);
        executeScript(script, source, target);
    }
}