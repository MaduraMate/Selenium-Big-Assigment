import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

public class SeleniumTests {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By usernameLocator = By.id("username");
    private final By passwordLocator = By.id("password");
    private final By loginButtonLocator = By.className("fa-sign-in");
    private final By flashLocator = By.id("flash");
    private final By logoutButtonLocator = By.className("button");

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }

    private WebElement waitVisibiiltyAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    @Test
    public void testSearchBar() {
        this.driver.get("http://the-internet.herokuapp.com/login");

        // Test login
        WebElement usernameTextField = waitVisibiiltyAndFindElement(usernameLocator);
        usernameTextField.sendKeys("tomsmith");

        WebElement passwordTextField = waitVisibiiltyAndFindElement(passwordLocator);
        passwordTextField.sendKeys("SuperSecretPassword!");

        WebElement loginButtonField = waitVisibiiltyAndFindElement(loginButtonLocator);
        loginButtonField.click();

        WebElement card = waitVisibiiltyAndFindElement(flashLocator);
        Assert.assertTrue(card.getText().contains("You logged into a secure area!"));

        // Logout
        WebElement logoutButton = waitVisibiiltyAndFindElement(logoutButtonLocator);
        logoutButton.click();

        // Test with wrong credentials
        usernameTextField = waitVisibiiltyAndFindElement(usernameLocator);
        usernameTextField.sendKeys("asd");

        passwordTextField = waitVisibiiltyAndFindElement(passwordLocator);
        passwordTextField.sendKeys("SuperSecretPassword!");

        loginButtonField = waitVisibiiltyAndFindElement(loginButtonLocator);
        loginButtonField.click();

        card = waitVisibiiltyAndFindElement(flashLocator);
        Assert.assertTrue(card.getText().contains("Your username is invalid!"));


        usernameTextField = waitVisibiiltyAndFindElement(usernameLocator);
        usernameTextField.sendKeys("tomsmith");

        passwordTextField = waitVisibiiltyAndFindElement(passwordLocator);
        passwordTextField.sendKeys("SuperSecret");

        loginButtonField = waitVisibiiltyAndFindElement(loginButtonLocator);
        loginButtonField.click();

        card = waitVisibiiltyAndFindElement(flashLocator);
        Assert.assertTrue(card.getText().contains("Your password is invalid!"));

    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
