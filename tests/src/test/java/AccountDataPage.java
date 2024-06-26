import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDataPage extends BaseFormPage {
    private By firstNameTextInput = By.xpath("//input[@name='lastname'][@class='form-control']");
    private By lastNameTextInput = By.xpath("//input[@name='firstname'][@class='form-control']");
    private By emailAdressTextInput = By.xpath("//input[@name='email'][@class='form-control']");

    public AccountDataPage(WebDriver driver) {
        super(driver);
    }

    public String getValueOfFirstNameTextInput() {
        return this.waitAndReturnElement(firstNameTextInput).getAttribute("value");
    }

    public String getValueOfLastNameTextInput() {
        return this.waitAndReturnElement(lastNameTextInput).getAttribute("value");
    }

    public String getValueOfEmailAddresstextInput() {
        return this.waitAndReturnElement(emailAdressTextInput).getAttribute("value");
    }

    public void changeFirstName(String firstName) {
        this.waitAndReturnElement(firstNameTextInput).clear();
        this.waitAndReturnElement(firstNameTextInput).sendKeys(firstName);
    }

    public void changeLastName(String lastName) {
        this.waitAndReturnElement(lastNameTextInput).clear();
        this.waitAndReturnElement(lastNameTextInput).sendKeys(lastName);
    }
}
