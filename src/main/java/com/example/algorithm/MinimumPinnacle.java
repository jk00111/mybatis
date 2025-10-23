package com.example.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinimumPinnacle {

    public static void main(String[] args) {
        MinimumPinnacle minimumPinnacle = new MinimumPinnacle();
        List<Integer> smallestSetOfVertices = minimumPinnacle.findSmallestSetOfVertices(5, new ArrayList<>(List.of(List.of(1, 3), List.of(2, 0), List.of(2, 3), List.of(1, 0), List.of(4, 1), List.of(0, 3))));

        for (Integer smallestSetOfVertex : smallestSetOfVertices) {
            System.out.println(smallestSetOfVertex);
        }
    }


    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        Set<Integer> nodes = IntStream.range(0, n).boxed().collect(Collectors.toSet());
        Set<Integer> destinations = new HashSet<>();
        for (List<Integer> edge : edges) {
            Integer destination = edge.get(1);
            destinations.add(destination);
        }

        nodes.removeAll(destinations);
        return new ArrayList<>(nodes);
    }
}
