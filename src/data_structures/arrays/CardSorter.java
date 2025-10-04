package data_structures.arrays;

import java.util.*;

public class CardSorter {
    public static void main(String[] args) {
        List<Integer> initialDeck = Arrays.asList(3, 5, 4, 1, 2);
        List<Integer> solved = CardSorter.solve(initialDeck);
        System.out.println(solved.toString());
        //solved.stream().mapToInt(Integer::intValue).forEach(System.out::print);
    }

    public static List<Integer> solve(List<Integer> initialDeck) {
        // Goal deck is 1 to n, sorted.
        List<Integer> goalDeck = new ArrayList<>();
        int n = initialDeck.size();
        for (int i = 1; i <= n; i++) {
            goalDeck.add(i);
        }

        // --- BFS setup ---
        Queue<List<Integer>> queue = new LinkedList<>();
        Set<List<Integer>> visited = new HashSet<>();
        Map<List<Integer>, List<List<Integer>>> path = new HashMap<>();

        // Add initial state to the queue and visited set.
        queue.add(initialDeck);
        visited.add(initialDeck);

        while (!queue.isEmpty()) {
            List<Integer> currentDeck = queue.poll();

            // Check if we've reached the goal state.
            if (currentDeck.equals(goalDeck)) {
                // Reconstruct the path of moves here and return it.
                // This part requires backtracking through the 'path' map.
                return currentDeck; // Or return the sequence of moves.
            }

            // Generate all possible next states.
            // The problem specifies k from 0 to n-1.
            for (int k = 0; k < n; k++) {
                List<Integer> newDeck = performShuffle(currentDeck, k);

                // If this state hasn't been visited, add it to the queue.
                if (!visited.contains(newDeck)) {
                    visited.add(newDeck);
                    queue.add(newDeck);
                    // Store the path.
                    // path.put(newDeck, currentDeck); 
                }
            }
        }

        // If the queue becomes empty and the goal is not reached,
        // it means the deck is not sortable with these moves.
        return null;
    }

    /**
     * This method performs the 'k' shuffle move.
     * It takes the top 'k' cards and moves them to the bottom.
     */
    private static List<Integer> performShuffle(List<Integer> deck, int k) {
        List<Integer> newDeck = new ArrayList<>(deck);
        if (k == 0) {
            // No cards are moved.
            return newDeck;
        }

        // Remove the top k cards.
        List<Integer> topCards = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            topCards.add(newDeck.remove(0));
        }

        // Add them to the bottom.
        newDeck.addAll(topCards);

        return newDeck;
    }
}