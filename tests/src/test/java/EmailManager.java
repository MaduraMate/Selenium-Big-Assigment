import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailManager extends PageBase {
    private WebDriver driver;
    By copyEmailAdress = By.xpath("//div[@class='input-warp']//button[@data-clipboard-action='copy']//span[text()='Copy']");

    public EmailManager(WebDriver driver) {
        super(driver);
        driver.get("https://temp-mail.org/en/");
    }

    public void copyEmailAddress() {
        this.waitAndReturnElement(copyEmailAdress).click();
    }
}
