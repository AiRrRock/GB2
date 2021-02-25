package ru.geekbrains.java.level2.lesson3;

import java.util.HashMap;
import java.util.Map;

public class UniqueWordsCounter {
    public static Map<String, Integer> calculateUniqueWordsAndCount(String[] words) {
        Map<String, Integer> uniqueWordsCount = new HashMap<>();
        for (String word : words) {
            //Have to use ++v because otherwise will always set value to 1;
            uniqueWordsCount.computeIfPresent(word, (k, v) -> ++v);
            //Thank you autoboxing for not making me cast int to Integer
            uniqueWordsCount.putIfAbsent(word, 1);
        }
        return uniqueWordsCount;
    }
}
