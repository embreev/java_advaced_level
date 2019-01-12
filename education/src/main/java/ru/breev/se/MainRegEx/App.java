package ru.breev.se.MainRegEx;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    static final Scanner scanner = new Scanner(System.in);

    private static boolean checkPassword(String pass) {
        Pattern pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\@\\#\\$\\$\\%])(?!.*[\\s]).{8,})");
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String pass = "";
        while (!pass.equals("exit")) {
            System.out.println("Enter password or 'exit':");
            pass = scanner.nextLine();
            System.out.println(checkPassword(pass));
        }

    }
}
