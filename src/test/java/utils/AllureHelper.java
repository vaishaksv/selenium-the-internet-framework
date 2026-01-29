package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

/**
 * Allure Report Helper - For enhanced test reporting
 */
public class AllureHelper {
    
    /**
     * Attach screenshot to Allure report
     */
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    
    /**
     * Attach text to Allure report
     */
    @Attachment(value = "{name}", type = "text/plain")
    public static String attachText(String name, String content) {
        return content;
    }
    
    /**
     * Attach HTML to Allure report
     */
    @Attachment(value = "{name}", type = "text/html")
    public static String attachHtml(String name, String html) {
        return html;
    }
    
    /**
     * Log step to Allure report
     */
    @Step("{description}")
    public static void logStep(String description) {
        LoggerUtil.info("Step: " + description);
    }
    
    /**
     * Log step with parameter
     */
    @Step("{description}: {param}")
    public static void logStep(String description, Object param) {
        LoggerUtil.info("Step: " + description + " = " + param);
    }
    
    /**
     * Add description to test
     */
    public static void setDescription(String description) {
        Allure.description(description);
    }
    
    /**
     * Add test severity
     */
    public static void setSeverity(io.qameta.allure.SeverityLevel level) {
        Allure.label("severity", level.value());
    }
    
    /**
     * Add feature label
     */
    public static void setFeature(String feature) {
        Allure.feature(feature);
    }
    
    /**
     * Add story label
     */
    public static void setStory(String story) {
        Allure.story(story);
    }
    
    /**
     * Add test case link
     */
    public static void setTestCaseLink(String url, String name) {
        Allure.link(name, url);
    }
}