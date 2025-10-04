package data_structures.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * A program to count the number of unique molecular pairings
 * that could potentially form stable bonds.
 */
public class MolecularBondCounter {

    /**
     * Calculates the number of unique molecular pairings.
     * The condition for a stable bond is x[i] - y[j] = x[j] - y[i],
     * which simplifies to x[i] + y[i] = x[j] + y[j].
     *
     * @param x The array of primary atomic weights.
     * @param y The array of secondary atomic weights.
     * @return The total number of valid molecular pairings (i, j) where i <= j.
     */
    public static long countStableBonds(int[] x, int[] y) {
        // A HashMap to store the frequency of each unique sum (x[k] + y[k]).
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Iterate through the arrays to calculate sums and store their frequencies.
        for (int i = 0; i < x.length; i++) {
            int sum = x[i] + y[i];
            frequencyMap.put(sum, frequencyMap.getOrDefault(sum, 0) + 1);
        }

        long totalPairs = 0;
        // Iterate through the frequencies in the map to calculate the total number of pairs.
        for (int frequency : frequencyMap.values()) {
            // For a group of 'n' elements with the same sum, the number of pairs (i, j)
            // where i <= j is given by the formula for triangular numbers: n * (n + 1) / 2.
            // This is the sum of integers from 1 to n (1 + 2 + ... + n).
            totalPairs += (long) frequency * (frequency + 1) / 2;
        }

        return totalPairs;
    }

    /**
     * Main method to test the solution with the provided example.
     */
    public static void main(String[] args) {
        // Example from the problem description
        int[] x = {2, -2, 5, 3};
        int[] y = {1, 5, -1, 1};

        long result = countStableBonds(x, y);

        System.out.println("The given arrays are:");
        System.out.print("x = [");
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + (i == x.length - 1 ? "" : ", "));
        }
        System.out.println("]");

        System.out.print("y = [");
        for (int i = 0; i < y.length; i++) {
            System.out.print(y[i] + (i == y.length - 1 ? "" : ", "));
        }
        System.out.println("]");
        
        System.out.println("The number of stable molecular pairings is: " + result);
        // The expected output is 6, as shown in the problem example.
    }
}
