package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InfiniteScrollPage;

public class InfiniteScrollTests extends BaseTest {
    
    @Test(description = "Verify infinite scroll loads more content")
    public void testInfiniteScroll() {
        InfiniteScrollPage page = new InfiniteScrollPage(driver);
        page.navigateTo();
        
        int initialCount = page.getParagraphCount();
        
        page.scrollMultipleTimes(3);
        
        int finalCount = page.getParagraphCount();
        
        Assert.assertTrue(finalCount > initialCount, 
            "More paragraphs should be loaded after scrolling");
    }
}