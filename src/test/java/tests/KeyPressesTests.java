package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.KeyPressesPage;

public class KeyPressesTests extends BaseTest {
    
    @Test(description = "Verify key presses are detected")
    public void testKeyPresses() {
        KeyPressesPage page = new KeyPressesPage(driver);
        page.navigateTo();
        
        page.pressEnter();
        Assert.assertTrue(page.getResultText().contains("ENTER"));
        
        page.pressEscape();
        Assert.assertTrue(page.getResultText().contains("ESC"));
        
        page.pressTab();
        Assert.assertTrue(page.getResultText().contains("TAB"));
        
        page.pressSpace();
        Assert.assertTrue(page.getResultText().contains("SPACE"));
    }
}