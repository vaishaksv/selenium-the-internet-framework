package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Enhanced Driver Manager with Grid support and better configuration
 */
public class DriverManager {
    
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String GRID_URL = System.getProperty("grid.url", "");
    
    public static WebDriver getDriver() {
        return driver.get();
    }
    
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }
    
    public static WebDriver initializeDriver(String browser, boolean headless) {
        LoggerUtil.info("Initializing " + browser + " driver (headless: " + headless + ")");
        
        WebDriver webDriver;
        
        // Check if running on Selenium Grid
        if (!GRID_URL.isEmpty()) {
            webDriver = createRemoteDriver(browser, headless);
        } else {
            webDriver = createLocalDriver(browser, headless);
        }
        
        configureDriver(webDriver);
        setDriver(webDriver);
        
        LoggerUtil.info("Driver initialized successfully");
        return webDriver;
    }
    
    private static WebDriver createLocalDriver(String browser, boolean headless) {
        switch (browser.toLowerCase()) {
            case "chrome":
                return createChromeDriver(headless);
            case "firefox":
                return createFirefoxDriver(headless);
            case "edge":
                return createEdgeDriver(headless);
            case "safari":
                return createSafariDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
    
    private static WebDriver createChromeDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", prefs);
        
        options.addArguments(
            "--disable-notifications",
            "--disable-popup-blocking",
            "--remote-allow-origins=*",
            "--disable-dev-shm-usage",
            "--no-sandbox"
        );
        
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }
        
        return new ChromeDriver(options);
    }
    
    private static WebDriver createFirefoxDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("browser.download.folderList", 2);
        
        if (headless) {
            options.addArguments("--headless");
        }
        
        WebDriver driver = new FirefoxDriver(options);
        if (!headless) {
            driver.manage().window().maximize();
        }
        return driver;
    }
    
    private static WebDriver createEdgeDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();
        
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notifications");
        
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }
        
        return new EdgeDriver(options);
    }
    
    private static WebDriver createSafariDriver() {
        WebDriver driver = new SafariDriver();
        driver.manage().window().maximize();
        return driver;
    }
    
    private static WebDriver createRemoteDriver(String browser, boolean headless) {
        try {
            // Implementation for Selenium Grid
            throw new UnsupportedOperationException("Grid support to be implemented");
        } catch (Exception e) {
            LoggerUtil.error("Failed to create remote driver, falling back to local", e);
            return createLocalDriver(browser, headless);
        }
    }
    
    private static void configureDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); // Use explicit waits
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        
        // Delete all cookies
        driver.manage().deleteAllCookies();
    }
    
    public static void quitDriver() {
        WebDriver webDriver = getDriver();
        if (webDriver != null) {
            try {
                LoggerUtil.info("Quitting driver");
                webDriver.quit();
            } catch (Exception e) {
                LoggerUtil.error("Error quitting driver", e);
            } finally {
                driver.remove();
            }
        }
    }
    
    public static String getBrowserInfo() {
        WebDriver driver = getDriver();
        if (driver instanceof RemoteWebDriver) {
            Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
            return caps.getBrowserName() + " " + caps.getBrowserVersion();
        }
        return "Unknown";
    }
}