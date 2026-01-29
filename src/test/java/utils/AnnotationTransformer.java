package utils;

import org.testng.annotations.*;

/**
 * Annotation Transformer - Adds retry analyzer to all tests automatically
 * No need to add @Test(retryAnalyzer = RetryAnalyzer.class) to each test
 */
public class AnnotationTransformer implements IAnnotationTransformer {
    
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, 
                         Constructor testConstructor, Method testMethod) {
        // Add retry analyzer to all tests
        if (annotation.getRetryAnalyzerClass() == null) {
            annotation.setRetryAnalyzerClass(RetryAnalyzer.class);
        }
        
        // Set default timeout if not specified (10 minutes)
        if (annotation.getTimeOut() == 0) {
            annotation.setTimeOut(600000); // 10 minutes
        }
        
        // Set default description if method name available
        if (annotation.getDescription() == null && testMethod != null) {
            annotation.setDescription("Test: " + testMethod.getName());
        }
    }
}