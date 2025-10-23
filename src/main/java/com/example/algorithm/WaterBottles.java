package com.example.algorithm;

public class WaterBottles {

    public static void main(String[] args) {
        WaterBottles waterBottles = new WaterBottles();
        System.out.println(waterBottles.numWaterBottles(15, 4));
    }

    public int numWaterBottles(int numBottles, int numExchange) {
        int count = 0;
        int emptyBottles = 0;

        while (true) {
            count += numBottles;

            int newBottles =  (numBottles + emptyBottles) / numExchange;
            int remainEmpty = (numBottles + emptyBottles) % numExchange;

            if (newBottles == 0) {
                break;
            }

            numBottles = newBottles;
            emptyBottles = remainEmpty;
        }

        return count;
    }
}
