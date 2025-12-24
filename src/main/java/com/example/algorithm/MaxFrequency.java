package com.example.algorithm;

import java.util.Arrays;

public class MaxFrequency {

    public static void main(String[] args) {
        MaxFrequency maxFrequency = new MaxFrequency();
//        System.out.println(maxFrequency.maxFrequency(new int[]{5, 11, 20, 20}, 5, 1));
//        System.out.println(maxFrequency.maxFrequency(new int[]{1, 90}, 76, 1));
//        System.out.println(maxFrequency.maxFrequency(new int[]{2}, 7, 0));
        System.out.println(maxFrequency.maxFrequency(new int[]{11, 71, 47}, 69, 1));
    }

    public int maxFrequency(int[] nums, int range, int numOperations) {
        int max = Arrays.stream(nums).max().orElseGet(() -> 0);
        int min = Arrays.stream(nums).min().orElseGet(() -> 0);

        for (int i = min; i <= max; i++) {

        }

        return 0;
    }

    private boolean canLeach(int target, int num, int range) {
        return target >= num -range && target <= num + range;
    }
}
