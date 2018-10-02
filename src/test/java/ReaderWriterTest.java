import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ReaderWriterTest {

    ReaderWriter readerWriter = ReaderWriter.getReaderWriter();
    final String fileName = "tempFile.txt";

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void writeToFile() throws Exception {
        final File tempFile = tempFolder.newFile(fileName);
        Map map = new HashMap();
        map.put("key1", 33d);
        map.put("key2", 2d);
        readerWriter.writeToFile(map, tempFile);
        final String s = FileUtils.readFileToString(tempFile);
        final String contains = "key1=33.0";
        assertTrue(s.contains(contains));
    }

    @Test
    public void readFile() throws Exception {
        final File tempFile = tempFolder.newFile(fileName);
        FileUtils.writeStringToFile(tempFile, "something");
        assertEquals("something", readerWriter.readFile(tempFile));
    }

}