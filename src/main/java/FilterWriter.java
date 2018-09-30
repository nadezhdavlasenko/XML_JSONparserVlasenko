import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class FilterWriter<T> {

    private static final FilterWriter filterWriter = new FilterWriter();

    private FilterWriter() {
    }

    public static FilterWriter getFilterWriter() {
        return filterWriter;
    }

    public void filterAnDWriteToFile(List<T> list, Predicate<T> predicate, File fileName) {
        //Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
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
