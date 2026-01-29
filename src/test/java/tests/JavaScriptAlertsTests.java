package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.JavaScriptAlertsPage;
import utils.Constants;

/**
 * Refactored JavaScript Alerts Tests
 */
public class JavaScriptAlertsTests extends BaseTest {
    
    private static final String TEST_INPUT = "Selenium Automation";
    
    @Test(description = "TC006: Verify JS Alert - accept only option")
    public void testJSAlertAccept() {
        JavaScriptAlertsPage page = new JavaScriptAlertsPage(driver);
        page.navigateTo();
        
        page.triggerAlert();
        
        Assert.assertTrue(actions.isAlertPresent(), 
            "Alert should be present");
        Assert.assertEquals(actions.getAlertText(), "I am a JS Alert", 
            "Alert text should match");
        
        actions.acceptAlert();
        
        Assert.assertEquals(page.getResultText(), "You successfully clicked an alert", 
            "Result message should be displayed");
    }
    
    @Test(description = "TC007: Verify JS Confirm - accept option")
    public void testJSConfirmAccept() {
        JavaScriptAlertsPage page = new JavaScriptAlertsPage(driver);
        page.navigateTo();
        
        page.triggerConfirm();
        
        Assert.assertEquals(actions.getAlertText(), "I am a JS Confirm", 
            "Confirm text should match");
        
        actions.acceptAlert();
        
        Assert.assertEquals(page.getResultText(), "You clicked: Ok", 
            "Result should show 'Ok'");
    }
    
    @Test(description = "TC008: Verify JS Confirm - dismiss option")
    public void testJSConfirmDismiss() {
        JavaScriptAlertsPage page = new JavaScriptAlertsPage(driver);
        page.navigateTo();
        
        page.triggerConfirm();
        actions.dismissAlert();
        
        Assert.assertEquals(page.getResultText(), "You clicked: Cancel", 
            "Result should show 'Cancel'");
    }
    
    @Test(description = "TC009: Verify JS Prompt - type text and accept")
    public void testJSPromptWithInput() {
        JavaScriptAlertsPage page = new JavaScriptAlertsPage(driver);
        page.navigateTo();
        
        page.triggerPrompt();
        
        Assert.assertEquals(actions.getAlertText(), "I am a JS prompt", 
            "Prompt text should match");
        
        actions.typeInAlert(TEST_INPUT);
        
        Assert.assertTrue(page.getResultText().contains(TEST_INPUT), 
            "Result should contain entered text: " + TEST_INPUT);
    }
    
    @Test(description = "TC010: Verify JS Prompt - dismiss without input")
    public void testJSPromptDismiss() {
        JavaScriptAlertsPage page = new JavaScriptAlertsPage(driver);
        page.navigateTo();
        
        page.triggerPrompt();
        actions.dismissAlert();
        
        Assert.assertEquals(page.getResultText(), "You entered: null", 
            "Result should show null for dismissed prompt");
    }
}