package utils;

import org.testng.asserts.SoftAssert;
import org.testng.Assert;

/**
 * Assertion Helper - Supports both Hard and Soft assertions
 * Use SoftAssert for multiple validations in one test
 */
public class AssertionHelper {
    
    private static final ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();
    
    /**
     * Initialize soft assert for current thread
     */
    public static void initSoftAssert() {
        softAssert.set(new SoftAssert());
    }
    
    /**
     * Get current thread's soft assert
     */
    public static SoftAssert getSoftAssert() {
        if (softAssert.get() == null) {
            initSoftAssert();
        }
        return softAssert.get();
    }
    
    /**
     * Assert all soft assertions
     */
    public static void assertAll() {
        if (softAssert.get() != null) {
            softAssert.get().assertAll();
            softAssert.remove();
        }
    }
    
    // ========== HARD ASSERTIONS ==========
    
    public static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }
    
    public static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }
    
    public static void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }
    
    public static void assertNotNull(Object object, String message) {
        Assert.assertNotNull(object, message);
    }
    
    public static void fail(String message) {
        Assert.fail(message);
    }
    
    // ========== SOFT ASSERTIONS ==========
    
    public static void softAssertTrue(boolean condition, String message) {
        getSoftAssert().assertTrue(condition, message);
    }
    
    public static void softAssertFalse(boolean condition, String message) {
        getSoftAssert().assertFalse(condition, message);
    }
    
    public static void softAssertEquals(Object actual, Object expected, String message) {
        getSoftAssert().assertEquals(actual, expected, message);
    }
    
    public static void softAssertNotNull(Object object, String message) {
        getSoftAssert().assertNotNull(object, message);
    }
}