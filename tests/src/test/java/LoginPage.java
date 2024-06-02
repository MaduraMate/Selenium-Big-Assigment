import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    By userNameTextField = By.xpath("//input[@id='email_login']");
    By passwordTextField = By.xpath("//input[@id='password_login']");
    By loginButton = By.xpath("//div[contains(@class, 'sr-login-row-login-button')]//button[contains(@class, 'btn-lg')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage login(String userName, String password) {
        this.waitAndReturnElement(userNameTextField).click();
        this.waitAndReturnElement(userNameTextField).sendKeys(userName);
        this.waitAndReturnElement(passwordTextField).click();
        this.waitAndReturnElement(passwordTextField).sendKeys(password);
        this.waitAndReturnElement(loginButton).click();

        return new AccountPage(driver);
    }
}
