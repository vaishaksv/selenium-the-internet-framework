package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DynamicLoadingPage;

public class DynamicLoadingTests extends BaseTest {
    
    @Test(description = "Verify hidden element appears after loading (Example 1)")
    public void testHiddenElementLoading() {
        DynamicLoadingPage page = new DynamicLoadingPage(driver);
        page.navigateTo();
        page.clickExample1();
        
        Assert.assertFalse(page.isFinishTextDisplayed(), "Text should be hidden initially");
        
        page.clickStart();
        page.waitForLoadingToComplete();
        
        Assert.assertTrue(page.isFinishTextDisplayed());
        Assert.assertEquals(page.getFinishText(), "Hello World!");
    }
    
    @Test(description = "Verify element rendered after loading (Example 2)")
    public void testRenderedElementLoading() {
        DynamicLoadingPage page = new DynamicLoadingPage(driver);
        page.navigateTo();
        page.clickExample2();
        
        Assert.assertFalse(page.isFinishTextDisplayed(), "Text should not exist initially");
        
        page.clickStart();
        page.waitForLoadingToComplete();
        
        Assert.assertTrue(page.isFinishTextDisplayed());
        Assert.assertEquals(page.getFinishText(), "Hello World!");
    }
}