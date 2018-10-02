import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

public class XMLCreatorTest {

    XMLCreator xmlCreator = XMLCreator.getXMLCreator();
    final String fileName = "tempFile.txt";

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void create() throws Exception {
        final File tempFile = tempFolder.newFile(fileName);
        xmlCreator.create(tempFile, Arrays.asList(new Person("vasia", "address", 2222, "NAU")));
        final String s = FileUtils.readFileToString(tempFile);
        final String xmlContains = "<catalog><notebook><person id=\"0\"><name>vasia<";
        // Verify the content
        assertTrue(s.contains(xmlContains));
        assertEquals("Done creating XML File\n", systemOutRule.getLogWithNormalizedLineSeparator());
    }


}
