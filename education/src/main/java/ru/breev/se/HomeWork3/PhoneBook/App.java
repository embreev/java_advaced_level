package ru.breev.se.HomeWork3.PhoneBook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class App {
    private final static ArrayList<User> phonebook = new ArrayList<>();

    private static void add(String surname, String phone) {
        if (!phonebook.isEmpty()) {
            for (int i = 0; i < phonebook.size(); i++) {
                if (phonebook.get(i).surname.equalsIgnoreCase(surname)) {
                    System.out.println("User " + surname + " exist! Update phone y/n?");
                    Scanner scanner = new Scanner(System.in);
                    if (scanner.nextLine().equalsIgnoreCase("y")) {
                        phonebook.get(i).phone.add(phone);
                    } else {
                        phonebook.add(new User(surname, phone));
                        break;
                    }
                }
            }
        } else {
            phonebook.add(new User(surname, phone));
        }
    }

    private static void get(String surname) {
        for (User user: phonebook) {
            System.out.println(user.surname + " " + user.phone);
            if (user.surname.equalsIgnoreCase(surname)) {
                System.out.println(surname + " " + user.phone);
            }
        }
    }

    public static void main(String[] args) {
        add("Ivanov", "89111111111");
        add("Petrov", "89222222222");
        add("Sidorov", "89333333333");
        add("Ivanov", "89444444444");
        add("Petrov", "89555555555");
        add("Sidorov", "89666666666");
        add("Ivanov", "89111111111");

//        get("Ivanov");
        get("Petrov");
        get("Sidorov");

    }
}

class User {
    final String surname;
    final HashSet<String> phone = new HashSet<>();

    public User(String surname, String phone) {
        this.surname = surname;
        this.phone.add(phone);
    }
}