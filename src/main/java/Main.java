import javax.xml.transform.TransformerConfigurationException;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Main {
    public static final String xmlFilePath = "xmlfile.xml";
    public static final File richPeoplePath = new File("richPeople.txt");
    public static final String currency = "currency.txt";

    public static void main(String[] args) throws TransformerConfigurationException {
        List<Person> people = Arrays.asList(
                new Person("John", "addr1", 99999, "KPI"),
                new Person("Vasia", "addr2", 99888, "KNEU"),
                new Person("Sasha", "addr3", 2222, "NAU")
        );
        XMLCreator.getXMLCreator().create(xmlFilePath, people);
        List<Person> peopleFromXML = XMLParser.getXMLParser().parseRichPeople(xmlFilePath);
        final int richBorder = 10000;
        Predicate<Person> predicate = x -> x.getCash() > richBorder;
        FilterWriter filterWriter = FilterWriter.getFilterWriter();
        filterWriter.filterAnDWriteToFile(peopleFromXML, predicate, richPeoplePath);
        filterWriter.filterAnDWriteToConsole(peopleFromXML, predicate);

        final String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        JSONParser jsonParser = JSONParser.getJsonParser();
        String jsonString = jsonParser.getJSONStringFromAPI(url);
        List<Map> list = jsonParser.getMapFromJSONString(jsonString);
        System.out.println(list);
        Map map = jsonParser.getMapFromList(list, "txt", "rate");
        System.out.println(map);
        ReaderWriter readerWriter = ReaderWriter.getReaderWriter();
        readerWriter.writeToFile(map, currency);
        System.out.println(readerWriter.readFile(currency));

    }
}