package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper {
    
    private static final String SCREENSHOT_DIR = "screenshots/";
    
    public static String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String fullPath = SCREENSHOT_DIR + fileName;
        
        try {
            File dir = new File(SCREENSHOT_DIR);
            if (!dir.exists()) dir.mkdirs();
            
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(fullPath);
            FileUtils.copyFile(src, dest);
            
            return fullPath;
        } catch (IOException e) {
            System.err.println("Screenshot failed: " + e.getMessage());
            return null;
        }
    }
    
    public static String takeBase64Screenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}