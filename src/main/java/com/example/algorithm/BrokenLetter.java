package com.example.algorithm;

import java.util.Arrays;
import java.util.List;

public class BrokenLetter {

    public static void main(String[] args) {
        BrokenLetter brokenLetter = new BrokenLetter();
        System.out.println(brokenLetter.canBeTypedWords("leet code", "lt"));
    }

    public int canBeTypedWords(String text, String brokenLetters) {
        int count;
        String[] words = text.split(" ");
        char[] brokenLettersCharArray = brokenLetters.toCharArray();

        List<String> collect = Arrays.stream(words).filter(word -> {
            for (char brokenLetter : brokenLettersCharArray) {
                if (word.contains(String.valueOf(brokenLetter))) {
                    return false;
                }
            }
            return true;
        }).toList();

        return collect.size();
    }
}
