package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DisappearingElementsPage;

public class DisappearingElementsTests extends BaseTest {
    
    @Test(description = "Verify disappearing elements count")
    public void testDisappearingElements() {
        DisappearingElementsPage page = new DisappearingElementsPage(driver);
        page.navigateTo();
        
        // Should have 4 or 5 elements depending on refresh
        int count = page.getMenuItemCount();
        Assert.assertTrue(count >= 4 && count <= 5, 
            "Menu should have 4 or 5 items, found: " + count);
    }
}