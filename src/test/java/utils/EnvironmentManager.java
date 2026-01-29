package utils;

/**
 * Environment Manager - Handle different environments (dev, staging, prod)
 */
public class EnvironmentManager {
    
    private static final String ENV = System.getProperty("env", "test");
    
    public enum Environment {
        DEV("https://dev-the-internet.herokuapp.com"),
        TEST("https://the-internet.herokuapp.com"),
        STAGING("https://staging-the-internet.herokuapp.com"),
        PROD("https://the-internet.herokuapp.com");
        
        private final String baseUrl;
        
        Environment(String baseUrl) {
            this.baseUrl = baseUrl;
        }
        
        public String getBaseUrl() {
            return baseUrl;
        }
    }
    
    /**
     * Get current environment
     */
    public static Environment getCurrentEnvironment() {
        try {
            return Environment.valueOf(ENV.toUpperCase());
        } catch (IllegalArgumentException e) {
            LoggerUtil.warn("Unknown environment: " + ENV + ", using TEST");
            return Environment.TEST;
        }
    }
    
    /**
     * Get base URL for current environment
     */
    public static String getBaseUrl() {
        return getCurrentEnvironment().getBaseUrl();
    }
    
    /**
     * Check if running in production
     */
    public static boolean isProduction() {
        return getCurrentEnvironment() == Environment.PROD;
    }
    
    /**
     * Get environment-specific config
     */
    public static Config getConfig() {
        return new Config(getCurrentEnvironment());
    }
    
    /**
     * Environment Configuration
     */
    public static class Config {
        private final Environment env;
        
        public Config(Environment env) {
            this.env = env;
        }
        
        public boolean shouldCaptureVideo() {
            return env == Environment.TEST || env == Environment.STAGING;
        }
        
        public boolean shouldUseTestData() {
            return env != Environment.PROD;
        }
        
        public int getRetryCount() {
            return env == Environment.PROD ? 1 : 2;
        }
        
        public String getReportName() {
            return "report-" + env.name().toLowerCase() + ".html";
        }
    }
}