import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


class MainPage extends PageBase {
    private By footerBy = By.className("footer-block");
    private By searchBarTogglerBy = By.xpath("//a[@class='search-bar-toggler']/i");
    private By searchBarBy = By.name("search");
    private By mapsAndGuidebookDropdownMenu = By.xpath("//li[@id='cat_139']//h3");
    private By mapsAndGuidebookOfEuropeButton = By.xpath("//a[@href='/terkep-es-utikonyv-139/uticel-szerint-140/foldreszek-141/europa-142']");
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://cartographia.hu/");
    }
    
    public By getMapsAndGuidebookOfEuropeButton() {
        return mapsAndGuidebookOfEuropeButton;
    }

    public String getFooterText() {
        return this.waitAndReturnElement(footerBy).getText();
    }
    
    public SearchResultPage search(String searchQuery) {
        this.waitAndReturnElement(searchBarTogglerBy).click();
        
        this.waitAndReturnElement(searchBarBy).sendKeys(searchQuery + "\n");
        return new SearchResultPage(this.driver);
    }

    public ListingGoods goToPage(By dropdownElement, By buttonToChangePage) {
        dropDownAndClickButton(dropdownElement, buttonToChangePage);

        return new ListingGoods(this.driver);
    }

    public By getMapsAndGuidebookDropdownMenu() {
        return mapsAndGuidebookDropdownMenu;
    }
}
