import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    By userNameTextField = By.xpath("//input[@id='email_login']");
    By passwordTextField = By.xpath("//input[@id='password_login']");
    By loginButton = By.xpath("//div[contains(@class, 'sr-login-row-login-button')]//button[contains(@class, 'btn-lg')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage login() {
        String username = "";
        String password = "";
        
        Pair<String, String> pair = TestHelper.readFromFile("resources/Auth.txt");
        username = pair.getLeft();
        password = pair.getRight();
        
        this.waitAndReturnElement(userNameTextField).click();
        this.waitAndReturnElement(userNameTextField).sendKeys(username);
        this.waitAndReturnElement(passwordTextField).click();
        this.waitAndReturnElement(passwordTextField).sendKeys(password);
        this.waitAndReturnElement(loginButton).click();

        return new AccountPage(driver);
    }
}
