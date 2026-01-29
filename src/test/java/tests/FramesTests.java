package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FramesPage;

public class FramesTests extends BaseTest {
    
    @Test(description = "Verify iFrame content editing")
    public void testIFrame() {
        FramesPage page = new FramesPage(driver);
        page.navigateTo();
        page.clickIframe();
        
        page.switchToIframe();
        page.typeInEditor("Hello from Selenium!");
        
        Assert.assertEquals(page.getEditorText(), "Hello from Selenium!");
        
        page.switchToMainContent();
    }
    
    @Test(description = "Verify nested frames content")
    public void testNestedFrames() {
        FramesPage page = new FramesPage(driver);
        page.navigateTo();
        page.clickNestedFrames();
        
        String leftText = page.getTextFromLeftFrame();
        String middleText = page.getTextFromMiddleFrame();
        String rightText = page.getTextFromRightFrame();
        String bottomText = page.getTextFromBottomFrame();
        
        Assert.assertEquals(leftText.trim(), "LEFT");
        Assert.assertEquals(middleText.trim(), "MIDDLE");
        Assert.assertEquals(rightText.trim(), "RIGHT");
        Assert.assertEquals(bottomText.trim(), "BOTTOM");
    }
}