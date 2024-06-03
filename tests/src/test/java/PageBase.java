import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;


class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    
    protected By goToLoginPageButton = By.xpath("//ul[@id='login_wrapper']/li/a[@href='index.php?route=account/login']");
    protected By accountManagerDropdown = By.xpath("//ul[@id='login_wrapper']/li/a[@href='index.php?route=account/account']");
    protected By textContainerOfAccountButton = By.xpath("//ul[@id='login_wrapper']/li/a[@href='index.php?route=account/account']//span");
    protected By logoutButton = By.xpath("//ul[@id='login_wrapper']/li//a[@href='index.php?route=account/logout']");
    protected By goToAccountPageButton = By.xpath("//a[@href='index.php?route=account/account'][@title='Fiók']");
    protected By logo = By.xpath("//div[@id='logo']//img[@title='Cartographia Kft.']");
    protected By pageTilte = By.xpath("//h1");

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
    }
    
    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    protected By calculateXPath(String attribute, int nth, int offset) {
        String[] splittedXPath = attribute.split("NNN");
        return By.xpath(splittedXPath[0] + (nth + offset) + splittedXPath[1]);
    }

    public WebElement getAccountManagerButton() {
        return this.waitAndReturnElement(accountManagerDropdown);
    }

    public String getTextOfGoToLoginPageButton() {
        return this.waitAndReturnElement(goToLoginPageButton).getText();
    }

    public String getTextOfAccountManagerButton() {
        return this.waitAndReturnElement(textContainerOfAccountButton).getText();
    }

    public LoginPage logout() {
        dropDownAndClickButton(accountManagerDropdown, logoutButton);

        return new LoginPage(this.driver);
    }

    public AccountPage goToAccountPage() {
        dropDownAndClickButton(accountManagerDropdown, goToAccountPageButton);

        return new AccountPage(this.driver);
    }

    public LoginPage goToLoginPage() {
        this.waitAndReturnElement(goToLoginPageButton).click();
        return new LoginPage(this.driver);
    }

    public MainPage goToMainPage() {
        waitAndReturnElement(logo).click();
        return new MainPage(driver);
    }

    public String getPageTitle() {
        return this.waitAndReturnElement(pageTilte).getText();
    }

    public void hover(By element) {
        WebElement webElement = this.waitAndReturnElement(element);
        actions.moveToElement(webElement).perform();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public PageBase dropDownAndClickButton(By dropdownElement, By buttonToChangePage) {
        hover(dropdownElement);
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonToChangePage));

        this.waitAndReturnElement(buttonToChangePage).click();

        return new PageBase(this.driver);
    }

    public PageBase goToPage(String url) {
        this.driver.get(url);
        return new PageBase(driver);
    }

    public PageBase pageBack() {
        driver.navigate().back();
        return this;
    }
}
