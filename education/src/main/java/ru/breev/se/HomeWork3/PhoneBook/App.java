package ru.breev.se.HomeWork3.PhoneBook;

import java.util.ArrayList;

public class App {
    final static ArrayList<User> phonebook = new ArrayList<>();

    private static void add(User user) {
        phonebook.add(user);
    }

    private static void get(String surname) {
        for (int i = 0; i < phonebook.size(); i++) {
            if (surname.equalsIgnoreCase(phonebook.get(i).surname)) {
                System.out.println(surname + " " + phonebook.get(i).phone);
            }
        }
    }

    public static void main(String[] args) {
        add(new User("Ivanov", "89111111111"));
        add(new User("Petrov", "89222222222"));
        add(new User("Sidorov", "89333333333"));
        add(new User("Ivanov", "89444444444"));
        add(new User("Petrov", "89555555555"));
        add(new User("Sidorov", "89666666666"));

        get("Ivanov");
        get("Petrov");
        get("Sidorov");
    }

}

class User {
    String surname;
    String phone;

    public User(String surname, String phone) {
        this.surname = surname;
        this.phone = phone;
    }
}