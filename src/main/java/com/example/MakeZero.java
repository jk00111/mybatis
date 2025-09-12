package com.example;

public class MakeZero {

    public static void main(String[] args) {
//        MakeZero makeZero = new MakeZero();
//        makeZero.makeTheIntegerZero(3, -2);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("run!!");
            }
        };
        Thread thread = new ReferThread(runnable, 5);
        thread.start();

        synchronized (new Object()) {

        }
    }

    static class ReferThread extends Thread {
        private int param;

        public ReferThread(Runnable target, int param) {
            super(target);
            this.param = param;
        }

        @Override
        public synchronized void start() {
            super.start();
            System.out.println("param is : " + param);
        }
    }


    //num2는 없다고 가정
    public int makeTheIntegerZero(int num1, int num2) {
        if (num2 > 0) {
            return isPositive(num1 , num2);
        }

        return isNegative(num1, num2);
    }

    private int isNegative(int num1, int num2) {
        int count = 0;

        while(num1 > 0) {
            num1 = num1 - num2;
            if (num1 < 0) break;

            int maximumTwoSquare = findMaximumTwoSquare(num1);
            num1 = num1 - maximumTwoSquare;
            count++;
        }

        if (num1 < 0) {
            return -1;
        }

        return count;
    }

    private int isPositive(int num1, int num2) {
        int count = 0;

        while(num1 > 0) {
            num1 = num1 - num2;
            if (num1 < 0) break;

            int maximumTwoSquare = findMaximumTwoSquare(num1);
            num1 = num1 - maximumTwoSquare;
            count++;
        }

        if (num1 < 0) {
            return -1;
        }

        return count;
    }


    private int findMaximumTwoSquare(int target) {
        int twoSquare = 1;

        while (twoSquare < target) {
            twoSquare = twoSquare * 2;
        }

        if (twoSquare == 1) {
            return twoSquare;
        }

        return twoSquare / 2;
    }


    // 2의 배수(0-60)배수 + num2의 갯수
    // 2의 0 승만 특이 하게 1을 뺄수 있음
    // 최대 2의 n승만큼을 계속 연산
    // num 2가
}
