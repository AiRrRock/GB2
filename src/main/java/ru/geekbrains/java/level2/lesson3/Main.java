package ru.geekbrains.java.level2.lesson3;

import static ru.geekbrains.java.level2.lesson3.UniqueWordsCounter.calculateUniqueWordsAndCount;

public class Main {
    public static void main(String[] args) {
        //Part 1 Unique words
        String[] words = {"A", "A", "A", "A", "A", "A", "B", "B", "B",
                "B", "B", "B", "B", "C", "C", "C", "C", "C", "C", "C"};
        System.out.println(calculateUniqueWordsAndCount(words));

        //Part 2 phone book nothing extra
        PhoneBook book = new PhoneBook();
        //Different numbers for same surname;
        book.add("A", "+71112341343");
        book.add("A", "+71112341345");
        // Same number (will only store one)
        book.add("B", "+71112341344");
        book.add("B", "+71112341344");
        // One number
        book.add("D", "+71112341343");
        book.add("C", "+71112341343");

        System.out.println(book.get("A"));
        System.out.println(book.get("B"));
        System.out.println(book.get("C"));
        System.out.println(book.get("D"));

    }


}
