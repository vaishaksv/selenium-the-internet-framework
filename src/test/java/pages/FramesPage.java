package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseActions;

/**
 * Frames Page - Contains iFrame and Nested Frames links
 */
public class FramesPage extends BaseActions {
    
    private final By iframeLink = By.linkText("iFrame");
    private final By nestedFramesLink = By.linkText("Nested Frames");
    
    // iFrame page elements
    private final By iframe = By.id("mce_0_ifr");
    private final By editorBody = By.id("tinymce");
    private final By formatMenu = By.cssSelector("button[title='Format']");
    
    // Nested frames
    private final By frameTop = By.name("frame-top");
    private final By frameLeft = By.name("frame-left");
    private final By frameMiddle = By.name("frame-middle");
    private final By frameRight = By.name("frame-right");
    private final By frameBottom = By.name("frame-bottom");
    private final By body = By.tagName("body");
    
    public FramesPage(WebDriver driver) {
        super(driver);
    }
    
    public FramesPage navigateTo() {
        navigateTo("https://the-internet.herokuapp.com/frames");
        return this;
    }
    
    public void clickIframe() {
        click(iframeLink);
    }
    
    public void clickNestedFrames() {
        click(nestedFramesLink);
    }
    
    // iFrame methods
    public void switchToIframe() {
        switchToFrame(iframe);
    }
    
    public void typeInEditor(String text) {
        clear(editorBody);
        type(editorBody, text);
    }
    
    public String getEditorText() {
        return getText(editorBody);
    }
    
    public void switchToMainContent() {
        switchToDefaultContent();
    }
    
    // Nested frames methods
    public String getTextFromLeftFrame() {
        switchToFrame(frameTop);
        switchToFrame(frameLeft);
        String text = getText(body);
        switchToDefaultContent();
        return text;
    }
    
    public String getTextFromMiddleFrame() {
        switchToFrame(frameTop);
        switchToFrame(frameMiddle);
        String text = getText(body);
        switchToDefaultContent();
        return text;
    }
    
    public String getTextFromRightFrame() {
        switchToFrame(frameTop);
        switchToFrame(frameRight);
        String text = getText(body);
        switchToDefaultContent();
        return text;
    }
    
    public String getTextFromBottomFrame() {
        switchToFrame(frameBottom);
        String text = getText(body);
        switchToDefaultContent();
        return text;
    }
}