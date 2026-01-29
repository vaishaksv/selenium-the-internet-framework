package utils;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Data Provider Factory - Centralized test data
 */
public class DataProviderFactory {
    
    /**
     * Provider for login test data
     */
    @DataProvider(name = "loginDataProvider", parallel = true)
    public static Object[][] getLoginData() {
        return new Object[][] {
            // username, password, shouldSucceed, description
            {"tomsmith", "SuperSecretPassword!", true, "Valid credentials"},
            {"invaliduser", "SuperSecretPassword!", false, "Invalid username"},
            {"tomsmith", "wrongpassword", false, "Invalid password"},
            {"", "", false, "Empty credentials"},
            {"tomsmith", "", false, "Empty password"},
            {"", "SuperSecretPassword!", false, "Empty username"}
        };
    }
    
    /**
     * Provider for alert test data
     */
    @DataProvider(name = "alertDataProvider")
    public static Object[][] getAlertData() {
        return new Object[][] {
            {"alert", "accept", "You successfully clicked an alert"},
            {"confirm", "accept", "You clicked: Ok"},
            {"confirm", "dismiss", "You clicked: Cancel"},
            {"prompt", "type:Hello", "You entered: Hello"}
        };
    }
    
    /**
     * Dynamic data provider based on method name
     */
    @DataProvider(name = "dynamicProvider")
    public static Iterator<Object[]> dynamicProvider(Method method) {
        String testName = method.getName();
        
        switch (testName) {
            case "testDropdownOptions":
                return Stream.of(
                    new Object[]{"Option 1", 1},
                    new Object[]{"Option 2", 2}
                ).iterator();
                
            case "testKeyPresses":
                return Stream.of(
                    new Object[]{org.openqa.selenium.Keys.ENTER, "ENTER"},
                    new Object[]{org.openqa.selenium.Keys.TAB, "TAB"},
                    new Object[]{org.openqa.selenium.Keys.SPACE, "SPACE"},
                    new Object[]{org.openqa.selenium.Keys.ESCAPE, "ESC"}
                ).iterator();
                
            default:
                return Stream.empty().iterator();
        }
    }
    
    /**
     * Provider with external data source capability
     */
    @DataProvider(name = "externalDataProvider")
    public static Object[][] getExternalData(ITestContext context) {
        // Could load from JSON, Excel, or Database
        return new Object[][] {
            {"data1", "value1"},
            {"data2", "value2"}
        };
    }
}