package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckboxesPage;

public class CheckboxesTests extends BaseTest {
    
    @Test(description = "Verify checkbox states")
    public void testCheckboxStates() {
        CheckboxesPage page = new CheckboxesPage(driver);
        page.navigateTo();
        
        // Initial state: first unchecked, second checked
        Assert.assertFalse(page.isCheckbox1Checked(), "Checkbox 1 should be unchecked initially");
        Assert.assertTrue(page.isCheckbox2Checked(), "Checkbox 2 should be checked initially");
        
        // Check first
        page.checkCheckbox1();
        Assert.assertTrue(page.isCheckbox1Checked(), "Checkbox 1 should be checked");
        
        // Uncheck second
        page.uncheckCheckbox2();
        Assert.assertFalse(page.isCheckbox2Checked(), "Checkbox 2 should be unchecked");
    }
    
    @Test(description = "Verify checkbox count")
    public void testCheckboxCount() {
        CheckboxesPage page = new CheckboxesPage(driver);
        page.navigateTo();
        
        Assert.assertEquals(page.getCheckboxCount(), 2, "Should have 2 checkboxes");
    }
}