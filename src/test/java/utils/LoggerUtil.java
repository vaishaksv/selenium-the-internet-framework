package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    
    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);
    
    public static void info(String msg) {
        logger.info(msg);
    }
    
    public static void error(String msg) {
        logger.error(msg);
    }
    
    public static void error(String msg, Throwable t) {
        logger.error(msg, t);
    }
    
    public static void debug(String msg) {
        logger.debug(msg);
    }
    
    public static void warn(String msg) {
        logger.warn(msg);
    }
    
    public static void startTest(String name) {
        logger.info("╔══════════════════════════════════════════════════════════╗");
        logger.info("║  STARTING TEST: " + String.format("%-40s", name) + "║");
        logger.info("╚══════════════════════════════════════════════════════════╝");
    }
    
    public static void endTest(String name, String status) {
        logger.info("╔══════════════════════════════════════════════════════════╗");
        logger.info("║  TEST " + status + ": " + String.format("%-36s", name) + "║");
        logger.info("╚══════════════════════════════════════════════════════════╝");
    }
}