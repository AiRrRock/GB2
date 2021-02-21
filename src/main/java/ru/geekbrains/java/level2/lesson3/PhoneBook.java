package ru.geekbrains.java.level2.lesson3;

import java.util.*;

public class PhoneBook {
    private final Map<String, List<String>> book;

    public PhoneBook() {
        book = new HashMap();
    }

    public void add(String surname, String number) {
        book.putIfAbsent(surname.toUpperCase(), new ArrayList<>(Arrays.asList(number)));
        book.computeIfPresent(surname, (k, v) -> {
            if (!v.contains(number)) {
                v.add(number);
            }
            return v;
        });
        //Dumb version
/*        String caseInsensitiveSurname = surname.toUpperCase();
        List<String> entry;
        if (book.containsKey(caseInsensitiveSurname)) {
            entry = book.get(caseInsensitiveSurname);
            if (!entry.contains(number)) {
                entry.add(number);
            }
        } else {
            entry = new ArrayList<>(Arrays.asList(number));
        }
        book.put(caseInsensitiveSurname, entry);*/
    }

    public List<String> get(String surname) {
        return book.getOrDefault(surname.toUpperCase(), Collections.emptyList());
    }
}
