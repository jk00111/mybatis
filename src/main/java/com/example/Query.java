package com.example;


/*
* 대문자 사용: 쿼리가 단어 목록에 있는 단어와 일치하는 경우( 대소문자 구분 없음 ), 쿼리 단어는 단어 목록에 있는 대소문자와 동일하게 반환됩니다.
예: wordlist = ["yellow"], query = "YellOw":correct = "yellow"
예: wordlist = ["Yellow"], query = "yellow":correct = "Yellow"
예: wordlist = ["yellow"], query = "yellow":correct = "yellow"
모음 오류: 쿼리 단어의 모음을 개별적으로 임의의 모음으로 바꾼 후 ('a', 'e', 'i', 'o', 'u'), 단어 목록에 있는 단어와 일치하는 경우( 대소문자 구분 없음 ), 쿼리 단어는 단어 목록에 있는 일치 항목과 동일한 대소문자로 반환됩니다.
예: wordlist = ["YellOw"], query = "yollow":correct = "YellOw"
예: wordlist = ["YellOw"], query = "yeellow": correct = ""(일치하지 않음)
예: wordlist = ["YellOw"], query = "yllw": correct = ""(일치하지 않음)
또한, 맞춤법 검사기는 다음과 같은 우선순위 규칙에 따라 작동합니다.

쿼리가 단어 목록에 있는 단어와 정확히 일치하는 경우( 대소문자 구분 ), 동일한 단어를 반환해야 합니다.
쿼리가 대문자로 시작하는 단어와 일치하는 경우 단어 목록에서 첫 번째로 일치하는 단어를 반환해야 합니다.
쿼리가 모음 오류까지 일치하는 단어를 발견하면 단어 목록에서 첫 번째로 일치하는 단어를 반환해야 합니다.
단어 목록에 쿼리와 일치하는 항목이 없으면 빈 문자열을 반환해야 합니다.
어떤 것이 주어지면 queries, 단어의 목록을 반환합니다 . answer여기서 는 answer[i]에 대한 올바른 단어입니다 query = queries[i]
* */

import java.util.*;
import java.util.stream.Collectors;


public class Query {

    public String[] spellchecker(String[] wordlist, String[] queries) {

        Checker checker = Checker.link(
                new EqualsChecker(),
                new CaseChecker(),
                new VowelChecker(),
                new EqualsChecker()
        );

        String[] answer = new String[queries.length];

        for (int i = 0; i < queries.length; i++) {
            answer[i] = checker.check(wordlist, queries[i]);
        }

        return answer;
    }


    public static class EndChecker extends Checker {

        @Override
        public String check(String[] wordList, String query) {
            return "";
        }
    }

    public static class VowelChecker extends Checker {

        private final Set<String> vowels = new HashSet<>(List.of(new String[]{"a", "e", "i", "o", "u"}));

        @Override
        public String check(String[] wordList, String query) {
            String replacedKey = query.replaceAll("[aeoui]", "*");
            Map<String, String> replacedMap = Arrays.stream(wordList).collect(Collectors.toMap(
                    word -> word.replaceAll("[aeoui]", "*"),
                    word -> word
            ));

            if (replacedMap.containsKey(replacedKey)) {
                return replacedMap.get(replacedKey);
            }

            return checkNext(wordList, query);
        }
    }

    public static class CaseChecker extends Checker {

        @Override
        public String check(String[] wordList, String query) {
            String queryKey = query.toLowerCase();
            Arrays.stream(wordList).collect(Collectors.toMap(
                    word -> new Correct(word.toLowerCase(), word),
                    word -> word
            ));

            Map<String, String> caseIgnoreMap = Arrays.stream(wordList).collect(Collectors.toMap(
                    String::toLowerCase,
                    word -> word
            ));

            if (caseIgnoreMap.containsKey(queryKey)) {
                return caseIgnoreMap.get(queryKey);
            }

            return checkNext(wordList, query);
        }
    }

    public static class EqualsChecker extends Checker {

        @Override
        public String check(String[] wordList, String query) {
            for (String word : wordList) {
                if (word.equals(query)) {
                    return query;
                }
            }

            return checkNext(wordList, query);
        }
    }


    public static abstract class Checker implements SpellChecker {
        private Checker next;

        public static Checker link(Checker first, Checker... chain) {
            Checker head = first;
            for (Checker nextInChain: chain) {
                head.next = nextInChain;
                head = nextInChain;
            }
            return first;
        }

        public String checkNext(String[] wordList, String query) {
            if (next != null) {
                next.check(wordList, query);
            }

            throw new RuntimeException();
        }
    }

    public static class Correct {

        private String key;
        private String value;

        public Correct(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Correct correct) {
                return this.key.equals(correct.key);
            }

            return false;
        }

        public String get(String key) {
            return value;
        }
    }

    interface SpellChecker {

        String check(String[] wordList, String query);
    }
}
