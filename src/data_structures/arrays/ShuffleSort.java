package data_structures.arrays;

import java.util.Arrays;

public class ShuffleSort {

    // 🟢 Function for you to implement
    public static int minShuffleMoves(int[] deck) {
        // TODO: Implement logic here
        // Return minimum number of moves required to sort deck
        // Return -1 if it's not possible
        return -1; // temporary placeholder
    }

    public static void main(String[] args) {
        // Example 1
        int[] deck1 = {3, 4, 5, 1, 2};
        System.out.println("Input: " + Arrays.toString(deck1));
        System.out.println("Minimum Moves: " + minShuffleMoves(deck1)); // Expected output: 1
        System.out.println();

        // Example 2
        int[] deck2 = {2, 3, 1, 5, 4};
        System.out.println("Input: " + Arrays.toString(deck2));
        System.out.println("Minimum Moves: " + minShuffleMoves(deck2)); // Expected output: -1
    }
}
