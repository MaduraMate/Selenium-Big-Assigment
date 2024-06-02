import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTests {
    WebDriver driver;
    MainPage mainPage;
    AccountPage accountPage;
    static String firstName;
    static String lastName;

    @BeforeClass
    public static void generateRandomData() {
        RandomDataGenerator.createRandomPerson();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/DataForTest.txt"))) {
            String line = bufferedReader.readLine();
            firstName = line.split(";")[0];
            lastName = line.split(";")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setup()  throws MalformedURLException  {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.goToLoginPage();
        accountPage = loginPage.login();
    }

    @Test
    public void testLogin() {
        assertEquals("Üdvözlünk " + firstName + "!", accountPage.getTextOfAccountManagerButton());
        assertEquals("Fiókom", accountPage.getPageTitle());
    }

    @Test
    public void testLogout() {
        LoginPage loginPage = accountPage.logout();
        assertEquals("Belépés", loginPage.getTextOfLoginPageButton());
    }

    @Test
    public void testStaticPage() {
        assertEquals("Üdvözlünk " + firstName + "!", accountPage.getTextOfAccountManagerButton());
        ListingGoods mapsOfEuropePage = mainPage.goToPage(mainPage.getMapsAndGuidebookDropdownMenu(), mainPage.getMapsAndGuidebookOfEuropeButton());
        assertEquals("Európa", mapsOfEuropePage.getPageTitle());

        // dropdown
        mapsOfEuropePage = mapsOfEuropePage.sortingGoodsBy(mapsOfEuropePage.getSortOption(2));
        assertEquals("A FÖLD ORSZÁGAI / KÖZÉP - EURÓPA AUTÓTÉRKÉP DUO TÉRKÉP 66 X 45 - STIEFEL", mapsOfEuropePage.getNameOfFirstItem());

        mapsOfEuropePage.clickOnSortingDropdown();
        assertEquals("Alapértelmezett", mapsOfEuropePage.getSortOption(1));
        assertEquals("Név, A - Z", mapsOfEuropePage.getSortOption(2));
        assertEquals("Név, Z - A", mapsOfEuropePage.getSortOption(3));
    }

    @Test
    public void multipleStaticPageTest() {
        Map<String, String> urlsAndTitles = new HashMap<String, String>();

        urlsAndTitles.put("https://cartographia.hu/terkep-es-utikonyv-139/uticel-szerint-140/foldreszek-141/europa-142", "Európa");
        urlsAndTitles.put("https://cartographia.hu/terkep-es-utikonyv-139/uticel-szerint-140/foldreszek-141/azsia-143", "Ázsia");
        urlsAndTitles.put("https://cartographia.hu/terkep-es-utikonyv-139/uticel-szerint-140/foldreszek-141/afrika-144", "Afrika");
        urlsAndTitles.put("https://cartographia.hu/terkep-es-utikonyv-139/uticel-szerint-140/foldreszek-141/amerika-145", "Amerika");
        urlsAndTitles.put("https://cartographia.hu/terkep-es-utikonyv-139/uticel-szerint-140/foldreszek-141/ausztralia-es-oceania-146", "Ausztrália és Óceánia");
        urlsAndTitles.put("https://cartographia.hu/terkep-es-utikonyv-139/uticel-szerint-140/foldreszek-141/antarktisz-147", "Antarktisz");
        
        mainPage = accountPage.goToMainPage();

        for (Map.Entry<String, String> entry : urlsAndTitles.entrySet()) {
            assertEquals(entry.getValue(), mainPage.goToPage(entry.getKey()).getPageTitle());
        }
    }

    @Test
    public void testEmailCheckingAfterRequestNewsletter() {
        NewsletterRequestPage newsletterRequestPage = accountPage.goToNewsletterRequestPage();
        newsletterRequestPage.requestsNewsletter();
        newsletterRequestPage.saveChanges();
        assertEquals("Sikeresen módosítottad a hírlevél beállításaid!", accountPage.getTextOfSuccessOfOperationTextArea());
        String emailBody = "";
        try {
            Thread.sleep(15000);
            emailBody = EmailManager.getEmailBody("imap.gmail.com", "imap");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(emailBody.contains("Ha ezeket a sorokat olvasod, akkor sikeresen feliratkoztál hírlevelünkre. Örülünk, hogy rátaláltál a Cartographia Kft. webáruházára!"));
        mainPage = new MainPage(driver);
        accountPage = mainPage.goToAccountPage();
        newsletterRequestPage = accountPage.goToNewsletterRequestPage();
        newsletterRequestPage.cancelNewsletter();
        newsletterRequestPage.saveChanges();
        assertEquals("Sikeresen módosítottad a hírlevél beállításaid!", accountPage.getTextOfSuccessOfOperationTextArea());
    }

    @Test
    public void testChangeFirstNameAndLastname() {
        AccountDataPage accountDataPage = accountPage.goToAccountDataPage();

        accountDataPage.changeFirstName(firstName);
        accountDataPage.changeLastName(lastName);

        accountPage = accountDataPage.saveChanges();
        assertEquals("A fiókadatok módosítása sikerült.", accountPage.getTextOfSuccessOfOperationTextArea());
        accountDataPage = accountPage.goToAccountDataPage();

        assertEquals("Fiók adataim", accountDataPage.getPageTitle());
        assertEquals(firstName, accountDataPage.getValueOfFirstNameTextInput());
        assertEquals(lastName, accountDataPage.getValueOfLastNameTextInput());
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
