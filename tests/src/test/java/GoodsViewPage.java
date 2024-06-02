import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoodsViewPage extends PageBase {
    private By publisher = By.xpath("//table[@class='product_parameters']//span[@itemprop='brand']");
    private By addToWhishlist = By.xpath("//a[@title='Kívánságlistára teszem']");

    public GoodsViewPage(WebDriver driver) {
        super(driver);
    }

    public String getPublisher() {
        return this.waitAndReturnElement(publisher).getText();
    }

    public WhishlistPage addToWhishlist() {
        this.waitAndReturnElement(addToWhishlist).click();
        return new WhishlistPage(driver);
    }
}
