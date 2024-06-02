import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends PageBase {
    private By changeOfAccountDataPageButton = By.xpath("//a[@href='https://cartographia.hu/index.php?route=account/edit']");
    private By successOfOperationTextArea = By.xpath("//div[@class='success']");
    private By goToNewsletterPageButton = By.xpath("//a[@href='https://cartographia.hu/index.php?route=account/newsletter']");

    AccountPage(WebDriver driver) {
        super(driver);
    }

    public AccountDataPage goToAccountDataPage() {
        this.waitAndReturnElement(changeOfAccountDataPageButton).click();
        return new AccountDataPage(this.driver);
    }

    public String getTextOfSuccessOfOperationTextArea() {
        return this.waitAndReturnElement(successOfOperationTextArea).getText();
    }

    public NewsletterRequestPage goToNewsletterRequestPage() {
        this.waitAndReturnElement(goToNewsletterPageButton).click();
        return new NewsletterRequestPage(this.driver);
    }
}
