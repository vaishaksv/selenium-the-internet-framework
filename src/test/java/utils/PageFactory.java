package utils;

import org.openqa.selenium.WebDriver;
import pages.*;

/**
 * Page Factory - Centralized page creation
 * Ensures pages are properly initialized
 */
public class PageFactory {
    
    private final WebDriver driver;
    private final BaseActions actions;
    
    public PageFactory(WebDriver driver) {
        this.driver = driver;
        this.actions = new BaseActions(driver);
    }
    
    public PageFactory(WebDriver driver, BaseActions actions) {
        this.driver = driver;
        this.actions = actions;
    }
    
    // Navigation methods that return initialized pages
    public HomePage getHomePage() {
        HomePage page = new HomePage(driver);
        page.navigateToHome();
        return page;
    }
    
    public AddRemoveElementsPage getAddRemoveElementsPage() {
        return new AddRemoveElementsPage(driver).navigateTo();
    }
    
    public CheckboxesPage getCheckboxesPage() {
        return new CheckboxesPage(driver).navigateTo();
    }
    
    public DropdownPage getDropdownPage() {
        return new DropdownPage(driver).navigateTo();
    }
    
    public JavaScriptAlertsPage getJavaScriptAlertsPage() {
        return new JavaScriptAlertsPage(driver).navigateTo();
    }
    
    public DragAndDropPage getDragAndDropPage() {
        return new DragAndDropPage(driver).navigateTo();
    }
    
    public HoversPage getHoversPage() {
        return new HoversPage(driver).navigateTo();
    }
    
    public FramesPage getFramesPage() {
        return new FramesPage(driver).navigateTo();
    }
    
    public MultipleWindowsPage getMultipleWindowsPage() {
        return new MultipleWindowsPage(driver).navigateTo();
    }
    
    public FormAuthenticationPage getFormAuthenticationPage() {
        return new FormAuthenticationPage(driver).navigateTo();
    }
    
    public DynamicLoadingPage getDynamicLoadingPage() {
        return new DynamicLoadingPage(driver).navigateTo();
    }
    
    public DataTablesPage getDataTablesPage() {
        return new DataTablesPage(driver).navigateTo();
    }
    
    public FileUploadPage getFileUploadPage() {
        return new FileUploadPage(driver).navigateTo();
    }
    
    public KeyPressesPage getKeyPressesPage() {
        return new KeyPressesPage(driver).navigateTo();
    }
    
    public DisappearingElementsPage getDisappearingElementsPage() {
        return new DisappearingElementsPage(driver).navigateTo();
    }
    
    public ChallengingDOMPage getChallengingDOMPage() {
        return new ChallengingDOMPage(driver).navigateTo();
    }
    
    public InfiniteScrollPage getInfiniteScrollPage() {
        return new InfiniteScrollPage(driver).navigateTo();
    }
}