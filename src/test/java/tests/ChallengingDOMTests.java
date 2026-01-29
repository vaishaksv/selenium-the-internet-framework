package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ChallengingDOMPage;

public class ChallengingDOMTests extends BaseTest {
    
    @Test(description = "Verify challenging DOM page loads")
    public void testChallengingDOM() {
        ChallengingDOMPage page = new ChallengingDOMPage(driver);
        page.navigateTo();
        
        Assert.assertTrue(page.isCanvasDisplayed(), "Canvas should be displayed");
        
        page.clickBlueButton();
        page.clickRedButton();
        page.clickGreenButton();
        
        Assert.assertEquals(page.getTableRowCount(), 10, "Table should have 10 rows");
    }
}