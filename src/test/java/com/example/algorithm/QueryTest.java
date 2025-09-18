package com.example.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

class QueryTest {
    @Test
    public void queryLogic() {
        String[] wordList = new String[]{"KiTe","kite","hare","Hare"};
        String[] queries = new String[]{"kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"};

        Query query = new Query();
        String[] answer = query.spellchecker(wordList, queries);

        String[] expected = new String[]{"kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"};

        assertArrayEquals(expected, answer);
    }
}