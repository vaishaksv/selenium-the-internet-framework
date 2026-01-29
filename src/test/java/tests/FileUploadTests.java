package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FileUploadPage;

public class FileUploadTests extends BaseTest {
    
    @Test(description = "Verify file upload functionality", enabled = false)
    public void testFileUpload() {
        // This test requires an actual file on the system
        // Enable and update file path to run
        
        FileUploadPage page = new FileUploadPage(driver);
        page.navigateTo();
        
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testdata/sample.txt";
        page.uploadFile(filePath);
        page.clickUpload();
        
        Assert.assertTrue(page.isFileUploaded("sample.txt"));
    }
}