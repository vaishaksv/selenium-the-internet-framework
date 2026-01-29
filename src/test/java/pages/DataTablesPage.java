package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseActions;

import java.util.List;

/**
 * Sortable Data Tables Page
 */
public class DataTablesPage extends BaseActions {
    
    private final By table1 = By.id("table1");
    private final By table2 = By.id("table2");
    
    // Headers for sorting
    private final By lastNameHeader = By.xpath("//table[@id='table1']//th[1]");
    private final By firstNameHeader = By.xpath("//table[@id='table1']//th[2]");
    private final By emailHeader = By.xpath("//table[@id='table1']//th[3]");
    private final By dueHeader = By.xpath("//table[@id='table1']//th[4]");
    private final By webSiteHeader = By.xpath("//table[@id='table1']//th[5]");
    
    public DataTablesPage(WebDriver driver) {
        super(driver);
    }
    
    public DataTablesPage navigateTo() {
        navigateTo("https://the-internet.herokuapp.com/tables");
        return this;
    }
    
    public void sortByLastName() {
        click(lastNameHeader);
    }
    
    public void sortByFirstName() {
        click(firstNameHeader);
    }
    
    public void sortByEmail() {
        click(emailHeader);
    }
    
    public void sortByDue() {
        click(dueHeader);
    }
    
    public String getCellText(int row, int col) {
        By cell = By.xpath("//table[@id='table1']/tbody/tr[" + row + "]/td[" + col + "]");
        return getText(cell);
    }
    
    public int getRowCount() {
        List<WebElement> rows = findAll(By.cssSelector("#table1 tbody tr"));
        return rows.size();
    }
    
    public void clickEdit(int row) {
        By editLink = By.xpath("//table[@id='table1']/tbody/tr[" + row + "]/td[6]/a[text()='edit']");
        click(editLink);
    }
    
    public void clickDelete(int row) {
        By deleteLink = By.xpath("//table[@id='table1']/tbody/tr[" + row + "]/td[6]/a[text()='delete']");
        click(deleteLink);
    }
}