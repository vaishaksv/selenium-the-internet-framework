package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseActions;

import java.util.List;

/**
 * File Upload Page
 */
public class FileUploadPage extends BaseActions {
    
    private final By fileInput = By.id("file-upload");
    private final By uploadButton = By.id("file-submit");
    private final By uploadedFiles = By.id("uploaded-files");
    private final By dragDropUpload = By.id("drag-drop-upload");
    
    public FileUploadPage(WebDriver driver) {
        super(driver);
    }
    
    public FileUploadPage navigateTo() {
        navigateTo("https://the-internet.herokuapp.com/upload");
        return this;
    }
    
    public void uploadFile(String filePath) {
        uploadFile(fileInput, filePath);
    }
    
    public void clickUpload() {
        click(uploadButton);
    }
    
    public String getUploadedFileName() {
        return getText(uploadedFiles);
    }
    
    public boolean isFileUploaded(String fileName) {
        return getUploadedFileName().contains(fileName);
    }
}