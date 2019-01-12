package ru.breev.se.HomeWork3.ArrayWord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App {
    static ArrayList<String> words = new ArrayList<>();
    static Map<String, Integer> uniqueWords = new HashMap<>();

    private static void countWord() {
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            int count = 0;
            for (int j = 0; j < words.size(); j++) {
                if (word.equals(words.get(j))) {
                    count++;
                }
            }
            uniqueWords.put(word, count);
        }
    }

    public static void main(String[] args) {
        words.add("apple");
        words.add("pinapple");
        words.add("strawbery");
        words.add("apple");
        words.add("bluebery");
        words.add("blackbery");
        words.add("banana");
        words.add("orange");
        words.add("lemon");
        words.add("lime");
        words.add("banana");
        words.add("cherry");
        words.add("apple");
        words.add("lemon");
        words.add("apple");
        words.add("pinapple");
        words.add("strawbery");
        words.add("apple");
        words.add("bluebery");
        words.add("blackbery");

        countWord();

        for (String item: uniqueWords.keySet()) {
            System.out.println(item + " = " + uniqueWords.get(item));
        }
    }
}
