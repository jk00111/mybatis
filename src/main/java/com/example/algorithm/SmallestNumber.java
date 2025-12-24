package com.example.algorithm;

public class SmallestNumber {

    public static void main(String[] args) {
        SmallestNumber smallestNumber = new SmallestNumber();
        smallestNumber.smallestNumber(3);
    }

    public int smallestNumber(int n) {
        String binaryString = Integer.toBinaryString(n);
        String replaced = binaryString.replaceAll("0", "1");
        return Integer.parseInt(replaced, 2);
    }
}
