package com.example.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaximumEnergy {

    public static void main(String[] args) {
        MaximumEnergy maximumEnergy = new MaximumEnergy();
        System.out.println(maximumEnergy.maximumEnergy(new int[]{5, 2, -10, -5, 1}, 3));
    }

    public int maximumEnergy(int[] energy, int gap) {
        return max(energy, gap);
    }

    private int max(int[] energy, int gap) {
        int max = Integer.MIN_VALUE;
        for (int i = energy.length - 1; i >= energy.length - 1 - gap; i--) {
            List<Integer> sumEnergies = sumEnergyReverse(energy, gap, i);
            Integer listMax = Collections.max(sumEnergies);
            if (max < listMax) {
                max = listMax;
            }
        }

        return max;
    }

    private List<Integer> sumEnergyReverse(int[] energy, int gap, int index) {
        List<Integer> sumList = new ArrayList<>();
        int sum = 0;
        for (int i = index; i >= 0; i-=gap) {
            sum += energy[i];
            sumList.add(sum);
        }

        return sumList;
    }
}
