package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DataTablesPage;

public class DataTablesTests extends BaseTest {
    
    @Test(description = "Verify table row count")
    public void testTableRowCount() {
        DataTablesPage page = new DataTablesPage(driver);
        page.navigateTo();
        
        Assert.assertEquals(page.getRowCount(), 4, "Should have 4 data rows");
    }
    
    @Test(description = "Verify sorting by last name")
    public void testSortByLastName() {
        DataTablesPage page = new DataTablesPage(driver);
        page.navigateTo();
        
        String beforeSort = page.getCellText(1, 1);
        page.sortByLastName();
        String afterSort = page.getCellText(1, 1);
        
        // Verify sort changed the order
        Assert.assertNotEquals(beforeSort, afterSort);
    }
    
    @Test(description = "Verify cell data retrieval")
    public void testCellData() {
        DataTablesPage page = new DataTablesPage(driver);
        page.navigateTo();
        
        String lastName = page.getCellText(1, 1);
        String firstName = page.getCellText(1, 2);
        String email = page.getCellText(1, 3);
        
        Assert.assertNotNull(lastName);
        Assert.assertNotNull(firstName);
        Assert.assertTrue(email.contains("@"));
    }
}