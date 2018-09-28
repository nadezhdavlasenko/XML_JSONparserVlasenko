import javax.xml.transform.TransformerConfigurationException;

public class Main {
    public static final String xmlFilePath = "C:\\Users\\Nadiia_Vlasenko\\IdeaProjects\\XMLparserVlasenko\\src\\main\\resources\\xmlfile.xml";

    public static void main(String[] args) throws TransformerConfigurationException {
        Creator.create(xmlFilePath);
    }
}