import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ReaderWriter {
    private static final ReaderWriter readerWriter = new ReaderWriter();

    private ReaderWriter() {
    }

    public static ReaderWriter getReaderWriter() {
        return readerWriter;
    }

    public void writeToFile(Map map, File fileName) {

//        Path path = Paths.get(fileName);

        //Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            writer.write(map + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(File fileName) {

        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
