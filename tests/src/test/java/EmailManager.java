import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Store;

public class EmailManager {
    //By copyEmailAdress = By.xpath("//button[@id='click-to-copy']");
    //By lastEmail = By.xpath("//div[@class='inbox-dataList']//li[2]//div[@class='col-box'][1]");
    //By emailBody = By.xpath("//div[@class='inbox-data-content-intro']//p[1]/");


    //public void copyEmailAddress() {
    //    try {
    //        Thread.sleep(10000);
    //    } catch (InterruptedException e) {
    //        e.printStackTrace();
    //    }
    //    this.waitAndReturnElement(copyEmailAdress).click();
    //}
//
    //public static void saveEmailAddress(String emailAddress) {
    //    try (PrintWriter printWriter = new PrintWriter(new File("resources/Email.txt"))) {
    //        printWriter.println(emailAddress);
    //    } catch (FileNotFoundException e) {
    //        e.printStackTrace();
    //    }
    //}
//
    //public static String readEmailAddressFromConfigFile() {
    //    try (BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/Email.txt"))) {
    //        return bufferedReader.readLine();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
//
    //    return "";
    //}
//
    //public String readEmailBody() {
    //    try {
    //        Thread.sleep(10000);
    //    } catch (InterruptedException e) {
    //        e.printStackTrace();
    //    }
    //    this.waitAndReturnElement(lastEmail).click();
    //    return this.waitAndReturnElement(emailBody).getText();
    //}


    private static Properties getServerProperties(String protocol, String host, String port) {
        System.out.println("Connecting please wait...");
       Properties properties = new Properties();

       properties.put("mail.pop3.host", "pop.gmail.com");
       properties.put("mail.pop3.port", Integer.toString(995));
       properties.put("mail.pop3.starttls.enable", "true");

       return properties;
    }

    public static String getEmailBody(String host, String storeType, String userName, String password) throws IOException, MessagingException {
		   
	      
        Properties properties = new Properties();

        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.starttls.enable", "true");
        properties.put("mail.imap.ssl.trust", host);

        Session emailSession = Session.getDefaultInstance(properties);

        // create the imap store object and connect to the imap server
        Store store = emailSession.getStore("imaps");

        store.connect(host, userName, password);

        // create the inbox object and open it
        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_WRITE);

        Message[] messages = inbox.getMessages();

        return messages[messages.length - 1].getSubject();
    }    


    //public static String getEmailBody(String username, String password, String 
    //    emailFolder) throws Exception {
    //    Properties properties = getServerProperties(protocol, host, port);
    //    Session session = Session.getDefaultInstance(properties);
    //    System.out.println("Connected to: "+host);
    //    Session session = Session.getDefaultInstance(properties);
    //
    //    //create the POP3 store object and connect with the pop server
    //    Store store = session.getStore("pop3s");
//
    //    store.connect(username, password);
//
    //    Folder folder = store.getFolder(emailFolder);
    //    folder.open(Folder.READ_WRITE);
//
    //    Message lastMessage = folder.getMessages()[0];
//
    //    if (!lastMessage.isSet(Flags.Flag.SEEN)) {
    //        System.out.println("hallohello");
    //        List<String> lines = new ArrayList<>();
    //        String line;
    //        BufferedReader reader = new BufferedReader(new InputStreamReader(lastMessage.getInputStream()));
    //        while ((line = reader.readLine()) != null) {
    //            lines.add(line);
    //        }
//
    //        return lines.get(2);
    //    }
//
    //    return "";
    //}
}
