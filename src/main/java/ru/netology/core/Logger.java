package ru.netology.core;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    static DateFormat dateFormat = new SimpleDateFormat("dd.MM HH:mm");

    public static void log(String text, File file) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("[" + dateFormat.format(new Date()) + "] " + text + "\n");
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
