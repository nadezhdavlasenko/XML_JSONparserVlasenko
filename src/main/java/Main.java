import javax.xml.transform.TransformerConfigurationException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
        Predicate<Person> predicate = x->x.getCash()>richBorder;

        FilterWriter filterWriter = FilterWriter.getFilterWriter();
        filterWriter.filterAnDWriteToFile(peopleFromXML, predicate, richPeoplePath);
        filterWriter.filterAnDWriteToConsole(peopleFromXML,predicate);
        final String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        JSONParser jsonParser = JSONParser.getJsonParser();
        String jsonString = jsonParser.getJSONStringFromAPI(url);
        //System.out.println(jsonString);
        List<Map> list = jsonParser.getMapFromJSONString(jsonString);
        System.out.println(list);
        Map map = jsonParser.getMapFromList(list,"txt", "rate");
        System.out.println(map);



    }
}