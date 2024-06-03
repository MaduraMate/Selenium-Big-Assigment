import org.openqa.selenium.WebDriver;

public class WhishlistPage extends PageBase {
    private String productName = "//table//tr[NNN]//div[@class='wishlist_product_name']";
    private String addToWhishlistDate = "//table//tr[NNN]//td[contains(@class,'wishlist-cell-added-value')]";

    public WhishlistPage(WebDriver driver) {
        super(driver);
    }

    public String getNameOfWhishlistProduct(int nth) {
        return this.waitAndReturnElement(calculateXPath(productName, nth, 1)).getText();
    }

    public String getAddDateOfWhishlistProduct(int nth) {
        return this.waitAndReturnElement(calculateXPath(addToWhishlistDate, nth, 1)).getText();
    }

    @Override
    public PageBase pageBack() {
        driver.navigate().back();
        return new GoodsViewPage(this.driver);
    }
}
