import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

public class XMLParserTest {

    final String fileName = "tempFile.txt";
    XMLParser xmlParser = XMLParser.getXMLParser();

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void parseRichPeople() throws Exception {
        final File tempFile = tempFolder.newFile(fileName);
        // Write something to it.
        FileUtils.writeStringToFile(tempFile, "<smth1><smth2><pers><name>dima</name><address>addr</address><cash>1000</cash></pers></smth2></smth1>");
        xmlParser.parsePeople(tempFile);
    }


}
