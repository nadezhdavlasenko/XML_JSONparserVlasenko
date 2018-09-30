import javax.xml.transform.TransformerConfigurationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static final String xmlFilePath = "xmlfile.xml";

    public static void main(String[] args) throws TransformerConfigurationException {
        List<Person> people = Arrays.asList(
                new Person("John","addr1", 99999, "KPI"),
                new Person("Vasia", "addr2", 99888, "KNEU"),
                new Person("Sasha", "addr3", 2222, "NAU")
        );
        XMLCreator.create(xmlFilePath, people);

        List<Person> peopleFromXML = XMLParser.parseRichPeople(xmlFilePath);

        //richPeople.stream().forEach(System.out::println);


    }
}