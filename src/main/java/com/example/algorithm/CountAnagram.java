package com.example.algorithm;

import java.util.HashMap;
import java.util.Map;

public class CountAnagram {

    private final long mod = 1_000_000_007;

    public static void main(String[] args) {
        CountAnagram countAnagram = new CountAnagram();
        System.out.println(countAnagram.countAnagrams("b okzojaporykbmq tybq zrztwlolvcyumcsq jjuowpp"));
    }

    public int countAnagrams(String s) {
        String[] words = s.split(" ");
        long answer = 1L;

        for (String word : words) {
            answer = modulo(answer) * modulo(countAnagram(word));
        }

        return (int) modulo(answer);
    }

    private long modulo(long num) {
        return num % mod;
    }

    private long countAnagram(String word) {
        int length = word.length();
        Map<Character, Integer> countMap = new HashMap<>();

        for (char c : word.toCharArray()) {
            if (countMap.containsKey(c)) {
                countMap.put(c, countMap.get(c) + 1);
                continue;
            }
            countMap.put(c, 1);
        }

        long denominator =1L;

        for (Integer value : countMap.values()) {
            denominator = modulo(denominator) * modulo(factorial(value));
        }


        return modulo(factorial(length)) * modulo(modInverse(denominator, mod));
    }

    private long modInverse(long a, long mod) {
        return modPow(a, mod - 2, mod); // a^(mod-2) % mod
    }

    private long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    private long factorial(int i) {
        long facto = 1L;
        while (i > 0) {
            facto = modulo(facto) * modulo(i);
            i--;
        }

        return facto;
    }
}
