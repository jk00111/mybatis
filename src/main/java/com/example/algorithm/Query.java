package com.example.algorithm;


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
                new EqualsChecker(createBasicWordMap(wordlist)),
                new CaseChecker(createLowerWordMap(wordlist)),
                new VowelChecker(createMaskWordMap(wordlist)),
                new EndChecker()
        );

        String[] answer = new String[queries.length];

        for (int i = 0; i < queries.length; i++) {
            answer[i] = checker.check(new BasicWord(queries[i]));
        }

        return answer;
    }

    private Map<Word, String> createBasicWordMap(String[] wordList) {
        return Arrays.stream(wordList)
                .collect(Collectors.toMap(
                        BasicWord::new,
                        word -> word,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    private Map<Word, String> createLowerWordMap(String[] wordList) {
        return Arrays.stream(wordList)
                .collect(Collectors.toMap(
                        LowerCaseWord::new,
                        word -> word,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }

    private Map<Word, String> createMaskWordMap(String[] wordList) {
        return Arrays.stream(wordList)
                .collect(Collectors.toMap(
                        MaskCaseWord::new,
                        word -> word,
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }


    public static class MaskCaseWord extends LowerCaseWord {

        private final String maskCase;

        public MaskCaseWord(String value) {
            super(value);
            this.maskCase = getMaskCaseElseNull(value);
        }

        public String getMaskCase() {
            return this.maskCase;
        }


        private String getMaskCaseElseNull(String value) {
            if (value == null || value.isEmpty()) {
                return null;
            }


            return getLowerCaseElseNull(value).replaceAll("[aeoui]", "*");
        }

        @Override
        public boolean equals(Object word) {
            if (word instanceof MaskCaseWord maskCaseWord) {
                return this.maskCase.equals(maskCaseWord.getMaskCase());
            }

            return false;
        }

        @Override
        public int hashCode() {
            return maskCase.hashCode();
        }
    }

    public static class LowerCaseWord extends Word {

        private final String lowerCase;

        public LowerCaseWord(String value) {
            super(value);
            this.lowerCase = getLowerCaseElseNull(value);
        }

        public String getLowerCase() {
            return this.lowerCase;
        }

        protected String getLowerCaseElseNull(String value) {
            if (value == null || value.isEmpty()) {
                return null;
            }

            return value.toLowerCase();
        }

        @Override
        public boolean equals(Object word) {
            if (word instanceof LowerCaseWord lowerCaseWord) {
                return this.lowerCase.equals(lowerCaseWord.getLowerCase());
            }

            return false;
        }

        @Override
        public int hashCode() {
            return lowerCase.hashCode();
        }
    }

    public static class BasicWord extends Word {

        public BasicWord(String value) {
            super(value);
        }

        @Override
        public boolean equals(Object word) {
            if (word instanceof BasicWord basicWord) {
                return this.getValue().equals(basicWord.getValue());
            }

            return false;
        }

        @Override
        public int hashCode() {
            return getValue().hashCode();
        }
    }

    public static abstract class Word {
        private final String value;


        public Word(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    public static class EndChecker extends Checker {

        @Override
        public String check(Word query) {
            return "";
        }

        @Override
        public Word convert(Word word) {
            return null;
        }
    }

    public static class VowelChecker extends Checker {

        private final Map<Word, String> wordList;

        public VowelChecker(Map<Word, String> wordList) {
            this.wordList = wordList;
        }

        @Override
        public String check(Word query) {
            Word convert = convert(query);

            if (wordList.containsKey(convert)) {
                return wordList.get(convert);
            }

            return checkNext(convert);
        }

        @Override
        public Word convert(Word word) {
            return new MaskCaseWord(word.getValue());
        }
    }

    public static class CaseChecker extends Checker {

        private final Map<Word, String> wordList;

        public CaseChecker(Map<Word, String> wordList) {
            this.wordList = wordList;
        }

        @Override
        public String check(Word query) {
            Word convert = convert(query);
            if (wordList.containsKey(convert)) {
                return wordList.get(convert);
            }

            return checkNext(convert);
        }

        @Override
        public Word convert(Word word) {
            return new LowerCaseWord(word.getValue());
        }
    }

    public static class EqualsChecker extends Checker {

        private final Map<Word, String> wordList;

        public EqualsChecker(Map<Word, String> wordList) {
            this.wordList = wordList;
        }

        @Override
        public String check(Word query) {
            if (wordList.containsKey(query)) {
                return wordList.get(query);
            }

            return checkNext(query);
        }

        @Override
        public Word convert(Word word) {
            return new BasicWord(word.getValue());
        }
    }


    public static abstract class Checker {

        private Checker next;

        public static Checker link(Checker first, Checker... chain) {
            Checker head = first;
            for (Checker nextInChain: chain) {
                head.next = nextInChain;
                head = nextInChain;
            }
            return first;
        }

        public String checkNext(Word query) {
            if (next != null) {
                return next.check(query);
            }

            throw new RuntimeException();
        }

        public abstract String check(Word query);
        public abstract Word convert(Word word);
    }
}
