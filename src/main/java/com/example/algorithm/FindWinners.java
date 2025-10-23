package com.example.algorithm;

import java.util.*;

public class FindWinners {

    private final Map<Player, Player> players = new HashMap<>();

    public static void main(String[] args) {
        int[][] matches = new int[][]{{1,3},{2,3},{3,6},{5,6},{5,7},{4,5},{4,8},{4,9},{10,4},{10,9}};
        FindWinners findWinners = new FindWinners();
        findWinners.findWinners(matches);
    }

    public List<List<Integer>> findWinners(int[][] matches) {
        for (int[] match : matches) {
            Player winner = new Player(match[0]);
            Player loser = new Player(match[1]);

            if (!players.containsKey(winner)) {
                players.put(winner, winner);
            }

            if (!players.containsKey(loser)) {
                players.put(loser, loser);
            }

            Player defeated = players.get(loser);
            defeated.defeat();
        }

        List<Integer> undefeated = players.values().stream().filter(winner -> winner.getDefeatCount() == 0).map(Player::id).sorted().toList();
        List<Integer> oneDefeated = players.values().stream().filter(winner -> winner.getDefeatCount() == 1).map(Player::id).sorted().toList();
        return new ArrayList<>(Arrays.asList(undefeated, oneDefeated));
    }

    static class Player {

        private final Integer id;
        private Integer defeatCount = 0;

        public Player(Integer id) {
            this.id = id;
        }

        public Integer id() {
            return this.id;
        }

        public Integer getDefeatCount() {
            return this.defeatCount;
        }

        public void defeat() {
            this.defeatCount++;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Player player = (Player) o;
            return Objects.equals(id, player.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
