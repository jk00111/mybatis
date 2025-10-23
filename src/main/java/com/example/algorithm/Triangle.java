package com.example.algorithm;

/*
* 배열 이 주어 지면 위에서 아래로 가는 경로의 최소 합을triangle 반환합니다 .

각 단계마다 바로 아래 행의 인접한 번호로 이동할 수 있습니다. 더 공식적으로, i현재 행의 인덱스에 있는 경우 인덱스 i또는 i + 1다음 행의 인덱스로 이동할 수 있습니다.
* */

import java.util.ArrayList;
import java.util.List;

public class Triangle {

    public static void main(String[] args) {
        List<Integer> one = new ArrayList<>(List.of(2));
        List<Integer> two = new ArrayList<>(List.of(3, 4));
        List<Integer> three = new ArrayList<>(List.of(6, 5, 7));
        List<Integer> four = new ArrayList<>(List.of(4, 1, 8 ,3));

        List<List<Integer>> input = new ArrayList<>(List.of(one, two, three, four));
        Triangle triangle = new Triangle();
        triangle.minimumTotal(input);

        ArrayList<List<Integer>> lists = new ArrayList<>(List.of(List.of(-10)));
        triangle.minimumTotal(lists);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >=0 ; i--) {
            List<Integer> upper = triangle.get(i);
            List<Integer> under = triangle.get(i+1);
            List<Integer> results = new ArrayList<>();

            for (int j = 0; j < upper.size(); j++) {
                results.add(upper.get(j) + min(under.get(j), under.get(j+1)));
            }

            triangle.set(i, results);
        }
        return triangle.get(0).get(0);
    }

    private Integer min(Integer i, Integer j) {
        if (i < j) {
            return i;
        }
        return j;
    }
}
