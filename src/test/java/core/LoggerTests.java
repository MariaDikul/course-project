package core;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.core.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerTests {
    static DateFormat dateFormat = new SimpleDateFormat("dd.MM HH:mm");


    @Test
    public void testLog() throws IOException {
        File dir = Files.createTempFile("dir", null).toFile();
        StringBuilder result = new StringBuilder();
        StringBuilder expected = new StringBuilder("[" + dateFormat.format(new Date()) + "] " + "hello");
        Logger.log("hello", dir);
        try (BufferedReader br = new BufferedReader(new FileReader(dir))) {
            String s;
            while ((s = br.readLine()) != null) {
                result.append(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        dir.delete();
        System.out.println(dir.exists());
        Assertions.assertEquals(expected.toString(), result.toString());
    }
}
