package com.example.algorithm;

import java.util.*;


public class FoodRatings {

    private final Map<String, Cuisine> cuisineMap = new HashMap<>();
    private final Map<String, String> foodMap = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; i++) {
            String cuisine = cuisines[i];
            foodMap.put(foods[i], cuisine);
            if (cuisineMap.containsKey(cuisine)){
                cuisineMap.get(cuisine).add(new Food(foods[i], ratings[i]));
                continue;
            }

            cuisineMap.put(cuisine, new Cuisine(cuisine, new Food(foods[i], ratings[i])));
        }
    }

    public void changeRating(String food, int newRating) {
        cuisineMap.get(foodMap.get(food))
                .changeRating(food, newRating);
    }

    public String highestRated(String cuisine) {
        return cuisineMap.get(cuisine).getHighest().name();
    }


    static public class Cuisine implements Iterable<Map.Entry<String, Food>> {

        private final Map<String, Food> foods = new HashMap<>();
        private final String name;
        private Food highestRatingFood;
        private Food historyHighest;

        public Cuisine(String name, Food init) {
            this.name = name;
            this.highestRatingFood = init;
            this.add(init);
        }

        private void challengeHighest(Food challenger) {
            if (isNewRecord(challenger)) {
                this.historyHighest = highestRatingFood;
                this.highestRatingFood = challenger;
                return;
            }

            if (isRatingEquals(challenger) && isDictionaryOrder(challenger)) {
                this.historyHighest = highestRatingFood;
                this.highestRatingFood = challenger;
            }
        }

        private boolean isNewRecord(Food challenger) {
            return this.highestRatingFood.rating() < challenger.rating();
        }

        private boolean isRatingEquals(Food challenger) {
            return this.highestRatingFood.rating() == challenger.rating();
        }

        private boolean isDictionaryOrder(Food challenger) {
            return this.highestRatingFood.name().compareTo(challenger.name()) > 0;
        }

        @Override
        public Iterator<Map.Entry<String, Food>> iterator() {
            return this.foods.entrySet().iterator();
        }


        public boolean contains(String foodName) {
            return foods.containsKey(foodName);
        }

        public void changeRating(String foodName, int rating) {
            Food food = this.foods.get(foodName);
            food.changeRating(rating);
            if (this.historyHighest.equals(food)) {
                deprived();
                return;
            }
            challengeHighest(food);
        }

        private void deprived() {

        }

        public Food get(String foodName) {
            return this.foods.get(foodName);
        }

        public Food getHighest() {
            return this.highestRatingFood;
        }

        public void add(Food food) {
            challengeHighest(food);
            this.foods.put(food.name(), food);
        }
    }


    static public class Food {

        private final String name;
        private int rating;

        public Food(String name, int rating) {
            this.name = name;
            this.rating = rating;
        }

        public String name() {
            return this.name;
        }

        public int rating() {
            return this.rating;
        }

        public void changeRating(int newRating) {
            this.rating = newRating;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Food food = (Food) o;
            return Objects.equals(name, food.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
