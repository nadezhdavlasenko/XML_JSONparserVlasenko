import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLCreator {

    private static final XMLCreator xmlCreator = new XMLCreator();

    private XMLCreator() {
    }

    public static XMLCreator getXMLCreator() {
        return xmlCreator;
    }

    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

    DocumentBuilder documentBuilder;

    {
        try {
            documentBuilder = documentFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();

        }
    }

    Document document = documentBuilder.newDocument();

    private final String CATALOG = "catalog";
    private final String NOTEBOOK = "notebook";
    private final String PERSON = "person";
    private final String NAME = "name";
    private final String ADDRESS = "address";
    private final String CASH = "cash";
    private final String EDUCATION = "education";

    public void create(String xmlFilePath, List<Person> people) throws TransformerConfigurationException {
        try {
            // root element
            Element root = document.createElement(CATALOG);
            document.appendChild(root);

            // notebook element
            Element notebook = document.createElement(NOTEBOOK);

            root.appendChild(notebook);

            int i = 0;
            for (Person p : people) {
                Element person = document.createElement(PERSON);
                notebook.appendChild(person);
                // set an attribute to staff element
                Attr attr = document.createAttribute("id");
                attr.setValue(String.valueOf(i++));
                person.setAttributeNode(attr);

                //you can also use staff.setAttribute("id", "1") for this

                // firstname element
                Element firstName = document.createElement(NAME);
                firstName.appendChild(document.createTextNode(p.getName()));
                person.appendChild(firstName);

                // lastname element
                Element lastname = document.createElement(ADDRESS);
                lastname.appendChild(document.createTextNode(p.getAddress()));
                person.appendChild(lastname);

                // email element
                Element email = document.createElement(CASH);
                email.appendChild(document.createTextNode(String.valueOf(p.getCash())));
                person.appendChild(email);

                // department elements
                Element department = document.createElement(EDUCATION);
                department.appendChild(document.createTextNode(p.getEducation()));
                person.appendChild(department);
            }

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging

            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File");

        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}
