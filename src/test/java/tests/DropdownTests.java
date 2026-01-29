package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DropdownPage;

public class DropdownTests extends BaseTest {
    
    @Test(description = "Verify dropdown selection by index")
    public void testDropdownByIndex() {
        DropdownPage page = new DropdownPage(driver);
        page.navigateTo();
        
        page.selectOption1();
        Assert.assertEquals(page.getSelectedOption(), "Option 1");
        
        page.selectOption2();
        Assert.assertEquals(page.getSelectedOption(), "Option 2");
    }
    
    @Test(description = "Verify dropdown selection by text and value")
    public void testDropdownByTextAndValue() {
        DropdownPage page = new DropdownPage(driver);
        page.navigateTo();
        
        page.selectByText("Option 1");
        Assert.assertEquals(page.getSelectedOption(), "Option 1");
        
        page.selectByValue("2");
        Assert.assertEquals(page.getSelectedOption(), "Option 2");
    }
}