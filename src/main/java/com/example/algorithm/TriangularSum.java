package com.example.algorithm;

public class TriangularSum {

    public static void main(String[] args) {
        TriangularSum triangularSum = new TriangularSum();
        int[] input = new int[]{0,3,3,4,1,2,6,4,9,3,5,1,7,7,3,0,3,2,5,1,9,0,2,6,3,9,2,5,9,2,6,4,2,9,7,2,0,3,0,1,1,2,7,8,6,4,4,5};
        System.out.println(triangularSum.triangularSum(input));
    }

    public int triangularSum(int[] nums) {
        return sum(nums)[0];
    }

    private int[] sum(int[] nums) {
        if (nums.length == 1) {
            return nums;
        }

        int[] sums = new int[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i++) {
            sums[i] = sumAndCutTens(nums[i], nums[i+1]);
        }

        return sum(sums);
    }

    private int sumAndCutTens(int i, int j) {
        int sum = i + j;
        if (sum > 9) {
            return sum - 10;
        }
        return sum;
    }
}
