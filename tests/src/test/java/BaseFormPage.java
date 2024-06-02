import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseFormPage extends PageBase {
    By saveData = By.xpath("//a[contains(@class,'button')]//span[text()='Tovább']");

    public BaseFormPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage saveChanges() {
        this.waitAndReturnElement(saveData).click();
        return new AccountPage(this.driver);
    }
}
