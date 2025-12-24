package com.example.algorithm;

import java.util.*;


// 3 2 1 1 2 3
// 3 2 0 0 2 3
//
public class MinOperations {

    public int minOperations(int[] nums) {

        // 분할


        // 정복


        //

        return 0;
    }

    private int getMin(int[] nums, int start, int end) {
        List<Integer> list = Arrays.stream(nums).boxed().toList();
        return list.subList(start, end).stream().min(Integer::compareTo).orElseGet(() -> 0);
    }
}
