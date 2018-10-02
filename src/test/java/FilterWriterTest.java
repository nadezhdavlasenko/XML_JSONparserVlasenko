import org.junit.Assert;
import org.junit.Rule;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class FilterWriterTest {

    List list = Arrays.asList("testList1", "testList2");
    Predicate predicate = p -> p.toString().contains("1");
    final String fileName = "tempFile.txt";
    FilterWriter filterWriter = FilterWriter.getFilterWriter();

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testFilterAnDWriteToFile() throws Exception {
        // Create a temporary file.
        final File tempFile = tempFolder.newFile(fileName);
        filterWriter.filterAnDWriteToFile(list, predicate, tempFile);
        // Read it from temp file
        final String s = FileUtils.readFileToString(tempFile);
        // Verify the content
        Assert.assertEquals("testList1\n", s);
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void filterAnDWriteToConsole() throws Exception {
        filterWriter.filterAnDWriteToConsole(list, predicate);
        assertEquals("testList1\n", systemOutRule.getLogWithNormalizedLineSeparator());
    }

}
