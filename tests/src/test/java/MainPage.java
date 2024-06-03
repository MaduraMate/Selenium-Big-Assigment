import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


class MainPage extends PageBase {
    private By mapsAndGuidebookDropdownMenu = By.xpath("//li[@id='cat_139']//h3");
    private By mapsAndGuidebookOfEuropeButton = By.xpath("//a[@href='/terkep-es-utikonyv-139/uticel-szerint-140/foldreszek-141/europa-142']");
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://cartographia.hu/");
    }

    public ListingGoodsPage goToPage(By dropdownElement, By buttonToChangePage) {
        dropDownAndClickButton(dropdownElement, buttonToChangePage);

        return new ListingGoodsPage(this.driver);
    }

    public By getMapsAndGuidebookDropdownMenu() {
        return mapsAndGuidebookDropdownMenu;
    }
    
    public By getMapsAndGuidebookOfEuropeButton() {
        return mapsAndGuidebookOfEuropeButton;
    }

    public String textOfMapsAndGuidebookOfEuropeButton() {
        return this.waitAndReturnElement(mapsAndGuidebookOfEuropeButton).getText();
    }
}
