package utils;

/**
 * Custom Exceptions for better error handling
 */
public class FrameworkException extends RuntimeException {
    
    public FrameworkException(String message) {
        super(message);
    }
    
    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Exception for element not found scenarios
     */
    public static class ElementNotFoundException extends FrameworkException {
        public ElementNotFoundException(String locator) {
            super("Element not found: " + locator);
        }
    }
    
    /**
     * Exception for timeout scenarios
     */
    public static class TimeoutException extends FrameworkException {
        public TimeoutException(String operation, int timeoutSeconds) {
            super("Operation timed out after " + timeoutSeconds + "s: " + operation);
        }
    }
    
    /**
     * Exception for invalid test data
     */
    public static class InvalidTestDataException extends FrameworkException {
        public InvalidTestDataException(String field, String value) {
            super("Invalid test data - Field: " + field + ", Value: " + value);
        }
    }
    
    /**
     * Exception for configuration errors
     */
    public static class ConfigurationException extends FrameworkException {
        public ConfigurationException(String configKey) {
            super("Configuration error for key: " + configKey);
        }
    }
}