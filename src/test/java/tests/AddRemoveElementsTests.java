package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class AddRemoveElementsTests extends BaseTest {
    
    @Test(description = "Verify adding elements")
    public void testAddElements() {
        AddRemoveElementsPage page = new AddRemoveElementsPage(driver);
        page.navigateTo();
        
        Assert.assertEquals(page.getDeleteButtonCount(), 0, "No delete buttons initially");
        
        page.addElements(3);
        Assert.assertEquals(page.getDeleteButtonCount(), 3, "Three delete buttons after adding");
    }
    
    @Test(description = "Verify deleting elements")
    public void testDeleteElements() {
        AddRemoveElementsPage page = new AddRemoveElementsPage(driver);
        page.navigateTo();
        
        page.addElements(3);
        Assert.assertEquals(page.getDeleteButtonCount(), 3);
        
        page.clickDelete(0);
        Assert.assertEquals(page.getDeleteButtonCount(), 2, "Two buttons after deletion");
        
        page.deleteAll();
        Assert.assertEquals(page.getDeleteButtonCount(), 0, "No buttons after deleting all");
    }
}