import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewsletterRequestPage extends BaseFormPage {
    private By requestNewsletterRadioButton = By.xpath("//input[@type='radio'][@value='1']");
    private By cancelNewsletterRadioButton = By.xpath("//input[@type='radio'][@value='0']");

    public NewsletterRequestPage(WebDriver driver) {
        super(driver);
    }

    public void cancelNewsletter() {
        this.waitAndReturnElement(cancelNewsletterRadioButton).click();
    }

    public void requestsNewsletter() {
        this.waitAndReturnElement(requestNewsletterRadioButton).click();
    }

}