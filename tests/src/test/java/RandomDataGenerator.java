import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;

public class RandomDataGenerator {
    private static Fairy fairy = Fairy.create();

    public static void createRandomPerson() {
        Person person = fairy.person(PersonProperties.male());
        try (PrintWriter printWriter = new PrintWriter(new File("DataForTest.txt"))) {
            printWriter.println(person.getFirstName() + ";" + person.getLastName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
