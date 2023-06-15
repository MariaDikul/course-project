package ru.netology.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PortLookup {
    public static int portLookup() {
        String port = null;
        try (BufferedReader br = new BufferedReader(new FileReader("settings.txt"))) {
            String s;
            while ((s = br.readLine()) != null) {
                s = s.trim();
                String cutString = s.substring(0, 4);
                if (cutString.equals("порт")) {
                    String[] stringArr = s.split(" ");
                    port = stringArr[stringArr.length - 1];
                } else {
                    System.out.println("Порт не найден");
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Integer.parseInt(port);
    }
}
