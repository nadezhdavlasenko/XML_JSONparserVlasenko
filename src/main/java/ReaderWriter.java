import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
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

    public void writeToFile(Map map, String fileName) {

        Path path = Paths.get(fileName);

        //Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            writer.write(map + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String fileName) {
        Path path = Paths.get(fileName);
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(path)) {


            while (reader.read() != -1) {
                result.append(reader.readLine());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
