package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * WebDriver Event Listener - Logs all driver actions
 */
public class DriverEventListener implements WebDriverListener {
    
    @Override
    public void beforeGet(WebDriver driver, String url) {
        LoggerUtil.debug("[BEFORE] Navigating to: " + url);
    }
    
    @Override
    public void afterGet(WebDriver driver, String url) {
        LoggerUtil.debug("[AFTER] Navigated to: " + url + " | Title: " + driver.getTitle());
    }
    
    @Override
    public void beforeClick(WebElement element) {
        LoggerUtil.debug("[BEFORE] Clicking on: " + getElementDescription(element));
    }
    
    @Override
    public void afterClick(WebElement element) {
        LoggerUtil.debug("[AFTER] Clicked on: " + getElementDescription(element));
    }
    
    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        LoggerUtil.debug("[BEFORE] Typing '" + Arrays.toString(keysToSend) + "' into: " + getElementDescription(element));
    }
    
    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        LoggerUtil.debug("[AFTER] Typed into: " + getElementDescription(element));
    }
    
    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        LoggerUtil.debug("[BEFORE] Finding element: " + locator);
    }
    
    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        LoggerUtil.debug("[AFTER] Found element: " + locator);
    }
    
    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        LoggerUtil.error("[ERROR] Exception in method: " + method.getName() + 
                        " | Error: " + e.getTargetException().getMessage());
    }
    
    private String getElementDescription(WebElement element) {
        try {
            String tag = element.getTagName();
            String text = element.getText();
            String id = element.getDomAttribute("id");
            String className = element.getDomAttribute("class");
            
            StringBuilder desc = new StringBuilder(tag);
            if (id != null && !id.isEmpty()) desc.append("#").append(id);
            if (className != null && !className.isEmpty()) desc.append(".").append(className.split(" ")[0]);
            if (!text.isEmpty()) desc.append(" (\"").append(text.substring(0, Math.min(20, text.length()))).append("\")");
            
            return desc.toString();
        } catch (Exception e) {
            return "[unknown element]";
        }
    }
}