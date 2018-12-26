package ru.breev.se.FileReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws IOException {
        final String path = Paths.get("").toAbsolutePath().normalize().toString();
        final String fileName = path + "\\file.txt";
        final char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
        final FileReader reader = new FileReader(fileName);
        int c;
        int line = 1;
        while ((c = reader.read()) != -1) {
            char temp = (char) c;
            int count = 0;
            if (c != 13) {
                for (int i = 0; i < vowels.length; i++) {
                    if (vowels[i] == temp) count += 1;
                }
            }
            System.out.println("In line = " + line + " vowels = " + count);
            if (c == 13) line += 1;
        }
    }
}
