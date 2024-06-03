import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListingGoodsPage extends PageBase {
    private By sortingOfGoodsDropdown = By.xpath("//select[@class='form-control']");
    private By firstItemOnPage = By.xpath("//div[@class='snapshot-list-container']//div[@class='snapshot-list-item list_prouctname'][1]");
    private String detailsButtonOfProduct = "//div[@class='snapshot-list-container']//div[contains(@class, 'list_div_item')][NNN]//a[@class='list-details-link']";

    ListingGoodsPage(WebDriver driver) {
        super(driver);
    }
    
    public ListingGoodsPage sortingGoodsBy(String sortBy) {
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

    public GoodsViewPage viewProduct(int nth) {
        this.waitAndReturnElement(this.calculateXPath(detailsButtonOfProduct, nth, 0)).click();
        return new GoodsViewPage(driver);
    }
}