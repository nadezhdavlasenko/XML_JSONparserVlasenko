import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class FilterWriter<T> {

    private static final FilterWriter filterWriter = new FilterWriter();

    private FilterWriter() {
    }

    public static FilterWriter getFilterWriter() {
        return filterWriter;
    }

    public void filterAnDWriteToFile(List<T> list, Predicate<T> predicate, String fileName) {

        Path path = Paths.get(fileName);

        //Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (T t : list) {


                if (predicate.test(t))
                    writer.write(t.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filterAnDWriteToConsole(List<T> list, Predicate<T> predicate) {
        list.stream().filter(predicate).forEach(System.out::println);
    }
}
