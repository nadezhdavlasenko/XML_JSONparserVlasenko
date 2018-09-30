import javax.xml.transform.TransformerConfigurationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static final String xmlFilePath = "xmlfile.xml";
    public static final String richPeoplePath = "richPeople.txt";

    public static void main(String[] args) throws TransformerConfigurationException {
        List<Person> people = Arrays.asList(
                new Person("John","addr1", 99999, "KPI"),
                new Person("Vasia", "addr2", 99888, "KNEU"),
                new Person("Sasha", "addr3", 2222, "NAU")
        );
        XMLCreator.create(xmlFilePath, people);

        List<Person> peopleFromXML = XMLParser.parseRichPeople(xmlFilePath);

        final int richBorder = 10000;
        Predicate<Person> predicate = x->x.getCash()> richBorder;

        FilterWriter filterWriter = FilterWriter.getFilterWriter();
        filterWriter.filterAnDWriteToFile(peopleFromXML, predicate, richPeoplePath);
        filterWriter.filterAnDWriteToConsole(peopleFromXML,predicate);
        //richPeople.stream().forEach(System.out::println);


    }
}