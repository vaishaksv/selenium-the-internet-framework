package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HoversPage;

public class HoversTests extends BaseTest {
    
    @Test(description = "Verify hover reveals caption")
    public void testHoverRevealsCaption() {
        HoversPage page = new HoversPage(driver);
        page.navigateTo();
        
        Assert.assertEquals(page.getFigureCount(), 3, "Should have 3 figures");
        
        // Hover over first figure
        page.hoverOverFigure(0);
        String caption = page.getCaptionText(0);
        
        Assert.assertTrue(caption.contains("user1"), "Caption should contain user1");
    }
    
    @Test(description = "Verify all figures can be hovered")
    public void testHoverAllFigures() {
        HoversPage page = new HoversPage(driver);
        page.navigateTo();
        
        for (int i = 0; i < 3; i++) {
            page.hoverOverFigure(i);
            String caption = page.getCaptionText(i);
            Assert.assertTrue(caption.contains("user" + (i + 1)), 
                "Figure " + i + " should have user" + (i + 1));
        }
    }
}