package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Enhanced BaseActions with retry logic, better waits, and error handling
 */
public class BaseActions {
    
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final WebDriverWait shortWait;
    protected final Actions actions;
    protected final JavascriptExecutor js;
    
    private static final int DEFAULT_TIMEOUT = 15;
    private static final int SHORT_TIMEOUT = 5;
    private static final int RETRY_ATTEMPTS = 3;
    
    public BaseActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }
    
    // ========== NAVIGATION ==========
    public void navigateTo(String url) {
        LoggerUtil.info("Navigating to: " + url);
        driver.get(url);
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    // ========== FIND ELEMENTS WITH RETRY ==========
    public WebElement find(By locator) {
        return retryOperation(() -> 
            wait.until(ExpectedConditions.presenceOfElementLocated(locator)),
            "Finding element: " + locator
        );
    }
    
    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }
    
    public WebElement waitForVisible(By locator) {
        return retryOperation(() -> 
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator)),
            "Waiting for visible: " + locator
        );
    }
    
    public WebElement waitForClickable(By locator) {
        return retryOperation(() -> 
            wait.until(ExpectedConditions.elementToBeClickable(locator)),
            "Waiting for clickable: " + locator
        );
    }
    
    // ========== CLICK ACTIONS ==========
    public void click(By locator) {
        retryOperation(() -> {
            waitForClickable(locator).click();
            return null;
        }, "Clicking on: " + locator);
    }
    
    public void safeClick(By locator) {
        try {
            click(locator);
        } catch (Exception e) {
            LoggerUtil.warn("Standard click failed, trying JS click for: " + locator);
            jsClick(locator);
        }
    }
    
    public void jsClick(By locator) {
        retryOperation(() -> {
            js.executeScript("arguments[0].click();", find(locator));
            return null;
        }, "JS clicking on: " + locator);
    }
    
    public void doubleClick(By locator) {
        retryOperation(() -> {
            actions.doubleClick(waitForVisible(locator)).perform();
            return null;
        }, "Double clicking on: " + locator);
    }
    
    public void rightClick(By locator) {
        retryOperation(() -> {
            actions.contextClick(waitForVisible(locator)).perform();
            return null;
        }, "Right clicking on: " + locator);
    }
    
    // ========== TEXT ACTIONS ==========
    public void type(By locator, String text) {
        if (text == null) return;
        
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
        LoggerUtil.debug("Typed '" + text + "' into: " + locator);
    }
    
    public void clear(By locator) {
        WebElement element = waitForVisible(locator);
        element.clear();
        // Ensure field is cleared
        if (!element.getDomAttribute("value").isEmpty()) {
            element.sendKeys(Keys.CONTROL + "a");
            element.sendKeys(Keys.DELETE);
        }
    }
    
    public String getText(By locator) {
        return waitForVisible(locator).getText();
    }
    
    public String getAttribute(By locator, String attribute) {
        WebElement element = waitForVisible(locator);
        String value = element.getDomAttribute(attribute);
        return value != null ? value : "";
    }
    
    // ========== CHECKBOX/RADIO ==========
    public void setCheckbox(By locator, boolean check) {
        WebElement element = waitForVisible(locator);
        boolean isSelected = element.isSelected();
        
        if (check && !isSelected) {
            element.click();
            LoggerUtil.debug("Checked: " + locator);
        } else if (!check && isSelected) {
            element.click();
            LoggerUtil.debug("Unchecked: " + locator);
        }
    }
    
    public boolean isSelected(By locator) {
        return waitForVisible(locator).isSelected();
    }
    
    // ========== DROPDOWN ==========
    private Select getSelect(By locator) {
        return new Select(waitForVisible(locator));
    }
    
    public void selectByText(By locator, String text) {
        LoggerUtil.debug("Selecting '" + text + "' from dropdown: " + locator);
        getSelect(locator).selectByVisibleText(text);
    }
    
    public void selectByValue(By locator, String value) {
        LoggerUtil.debug("Selecting value '" + value + "' from dropdown: " + locator);
        getSelect(locator).selectByValue(value);
    }
    
    public void selectByIndex(By locator, int index) {
        LoggerUtil.debug("Selecting index " + index + " from dropdown: " + locator);
        getSelect(locator).selectByIndex(index);
    }
    
    public String getSelectedOption(By locator) {
        return getSelect(locator).getFirstSelectedOption().getText();
    }
    
    public List<WebElement> getOptions(By locator) {
        return getSelect(locator).getOptions();
    }
    
    // ========== ALERTS ==========
    public void acceptAlert() {
        waitForAlert();
        driver.switchTo().alert().accept();
        LoggerUtil.debug("Alert accepted");
    }
    
    public void dismissAlert() {
        waitForAlert();
        driver.switchTo().alert().dismiss();
        LoggerUtil.debug("Alert dismissed");
    }
    
    public String getAlertText() {
        waitForAlert();
        return driver.switchTo().alert().getText();
    }
    
    public void typeInAlert(String text) {
        waitForAlert();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
        LoggerUtil.debug("Typed '" + text + "' in alert");
    }
    
    public boolean isAlertPresent() {
        try {
            shortWait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    public void waitForAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
    }
    
    // ========== FRAMES ==========
    public void switchToFrame(By locator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
        LoggerUtil.debug("Switched to frame: " + locator);
    }
    
    public void switchToFrame(int index) {
        driver.switchTo().frame(index);
    }
    
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
        LoggerUtil.debug("Switched to default content");
    }
    
    // ========== WINDOWS ==========
    public void switchToWindow(String handle) {
        driver.switchTo().window(handle);
        LoggerUtil.debug("Switched to window: " + handle.substring(0, Math.min(10, handle.length())) + "...");
    }
    
    public String getCurrentWindow() {
        return driver.getWindowHandle();
    }
    
    public java.util.Set<String> getAllWindows() {
        return driver.getWindowHandles();
    }
    
    public void switchToNewWindow() {
        String current = getCurrentWindow();
        wait.until(d -> getAllWindows().size() > 1);
        
        for (String window : getAllWindows()) {
            if (!window.equals(current)) {
                switchToWindow(window);
                break;
            }
        }
    }
    
    public void closeWindow() {
        driver.close();
    }
    
    // ========== MOUSE ACTIONS ==========
    public void hover(By locator) {
        retryOperation(() -> {
            actions.moveToElement(waitForVisible(locator)).perform();
            return null;
        }, "Hovering over: " + locator);
    }
    
    public void dragAndDrop(By source, By target) {
        retryOperation(() -> {
            actions.dragAndDrop(waitForVisible(source), waitForVisible(target)).perform();
            return null;
        }, "Dragging from " + source + " to " + target);
    }
    
    public void dragAndDropByOffset(By locator, int x, int y) {
        retryOperation(() -> {
            actions.dragAndDropBy(waitForVisible(locator), x, y).perform();
            return null;
        }, "Dragging by offset (" + x + ", " + y + ")");
    }
    
    // ========== SCROLLING ==========
    public void scrollToElement(By locator) {
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", 
            find(locator));
    }
    
    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    
    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }
    
    // ========== JAVASCRIPT ==========
    public Object executeScript(String script, Object... args) {
        return js.executeScript(script, args);
    }
    
    // ========== WAITS ==========
    public void waitForInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    public void waitForText(By locator, String text) {
        wait.until(ExpectedConditions.textToBe(locator, text));
    }
    
    public void waitForTextContains(By locator, String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    public void waitForPageLoad() {
        wait.until(d -> js.executeScript("return document.readyState").equals("complete"));
    }
    
    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // ========== UPLOAD ==========
    public void uploadFile(By locator, String filePath) {
        // File upload doesn't need visibility, send keys directly to input
        find(locator).sendKeys(filePath);
        LoggerUtil.debug("Uploaded file: " + filePath);
    }
    
    // ========== STATE CHECKS ==========
    public boolean isDisplayed(By locator) {
        try {
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    public boolean isEnabled(By locator) {
        try {
            return waitForVisible(locator).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isPresent(By locator) {
        return !findAll(locator).isEmpty();
    }
    
    // ========== RETRY UTILITY ==========
    private <T> T retryOperation(java.util.function.Supplier<T> operation, String description) {
        Exception lastException = null;
        
        for (int i = 0; i < RETRY_ATTEMPTS; i++) {
            try {
                if (i > 0) {
                    LoggerUtil.warn("Retry attempt " + (i + 1) + " for: " + description);
                    waitSeconds(1);
                }
                return operation.get();
            } catch (StaleElementReferenceException | ElementNotInteractableException e) {
                lastException = e;
                LoggerUtil.warn("Element issue on attempt " + (i + 1) + ": " + e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException("Operation failed: " + description, e);
            }
        }
        
        throw new RuntimeException("Operation failed after " + RETRY_ATTEMPTS + " attempts: " + description, lastException);
    }
}