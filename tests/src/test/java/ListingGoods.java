import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListingGoods extends PageBase {
    private By sortingOfGoodsDropdown = By.xpath("//select[@class='form-control']");
    private By firstItemOnPage = By.xpath("//div[@class='snapshot-list-container']//div[@class='snapshot-list-item list_prouctname'][1]");
    

    ListingGoods(WebDriver driver) {
        super(driver);
    }
    
    public ListingGoods sortingGoodsBy(String sortBy) {
        this.waitAndReturnElement(sortingOfGoodsDropdown).click();
        By sortingGoodBy = By.xpath("//option[text()='" + sortBy + "']");
        
        this.waitAndReturnElement(sortingGoodBy).click();

        return this;
    }

    public String getNameOfFirstItem() {
        return this.waitAndReturnElement(firstItemOnPage).getText();
    }

    public void clickOnSortingDropdown() {
        this.waitAndReturnElement(sortingOfGoodsDropdown).click();
    }

    public String getSortOption(int n) {
        if (n > 0 && n < 10) {
            String xpath = "//select[@class='form-control']//option["+ n +"]";
            return this.waitAndReturnElement(By.xpath(xpath)).getText();
        }

        return "";
    }
}