import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;

public class TestHelper {
    private static Fairy fairy = Fairy.create();

    public static void createRandomPerson() {
        Person person = fairy.person(PersonProperties.male());
        try (PrintWriter printWriter = new PrintWriter(new File("resources/DataForTest.txt"))) {
            printWriter.println(person.getFirstName() + ";" + person.getLastName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Pair<String, String> readFromFile(String fileName) {
        String key = "";
        String value = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            key = line.split(";")[0];
            value = line.split(";")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new MutablePair<String,String>(key, value);
    }
}
