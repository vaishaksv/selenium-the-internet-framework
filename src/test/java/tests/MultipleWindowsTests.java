package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MultipleWindowsPage;

public class MultipleWindowsTests extends BaseTest {
    
    @Test(description = "Verify new window opens and has correct content")
    public void testNewWindow() {
        MultipleWindowsPage page = new MultipleWindowsPage(driver);
        page.navigateTo();
        
        String originalWindow = driver.getWindowHandle();
        
        page.clickClickHere();
        page.switchToNewWindow();
        
        Assert.assertEquals(page.getNewWindowText(), "New Window");
        
        page.closeNewWindowAndSwitchBack();
        
        Assert.assertEquals(driver.getWindowHandle(), originalWindow);
    }
}