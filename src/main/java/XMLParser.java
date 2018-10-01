import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private static final XMLParser xmlParser = new XMLParser();

    private XMLParser() {
    }

    public static XMLParser getXMLParser() {
        return xmlParser;
    }
    private final String NAME = "name";
    private final String ADDRESS = "address";
    private final String CASH = "cash";

    public List<Person> parseRichPeople(File fileName) {


        List<Person> people = new ArrayList<>();
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(fileName);

            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            System.out.println("List of persons:");
            System.out.println();
            Node notebook = root.getFirstChild();
            // Просматриваем все подэлементы корневого
            NodeList peopleNodes = notebook.getChildNodes();
            for (int i = 0; i < peopleNodes.getLength(); i++) {
                Node person = peopleNodes.item(i);
                // Если нода не текст, то это книга - заходим внутрь
                if (person.getNodeType() != Node.TEXT_NODE) {
                    NodeList personProps = person.getChildNodes();
                    Person p = new Person();
                    p.setName(((Element) person).getElementsByTagName(NAME).item(0).getTextContent());
                    p.setAddress(((Element) person).getElementsByTagName(ADDRESS).item(0).getTextContent());
                    p.setCash(Integer.valueOf(((Element) person).getElementsByTagName(CASH).item(0).getTextContent()));
                    people.add(p);
                    for (int j = 0; j < personProps.getLength(); j++) {
                        Node personProp = personProps.item(j);
                        // Если нода не текст, то это один из параметров книги - печатаем
                        if (personProp.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(personProp.getNodeName() + ":" + personProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    System.out.println("===========>>>>");
                }
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return people;
    }
}
