package data_structures.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Solves the puzzle of arranging 4x4 squares based on their missing number.
 */
public class PuzzleSolver {

    /**
     * A helper class to store a 4x4 matrix, its original index, and the missing value.
     * This makes sorting more convenient.
     */
    static class SquareInfo implements Comparable<SquareInfo> {
        int originalIndex;
        int missingValue;
        String[][] square;

        SquareInfo(int originalIndex, int missingValue, String[][] square) {
            this.originalIndex = originalIndex;
            this.missingValue = missingValue;
            this.square = square;
        }

        // Custom comparison logic for sorting.
        // Sorts by missing value in ascending order.
        // For ties, the original relative order is maintained due to stable sort properties.
        @Override
        public int compareTo(SquareInfo other) {
            return Integer.compare(this.missingValue, other.missingValue);
        }
    }

    /**
     * Finds the missing number in a given 4x4 square and replaces the "?" with it.
     *
     * @param square The 4x4 square matrix as a String[][].
     * @return The missing integer value.
     */
    private static int findAndReplaceMissing(String[][] square) {
        Set<Integer> numbers = new HashSet<>();
        int missingVal = -1;
        
        // Find the missing number by checking which numbers from 1 to 16 are present.
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!square[i][j].equals("?")) {
                    numbers.add(Integer.parseInt(square[i][j]));
                }
            }
        }

        for (int i = 1; i <= 16; i++) {
            if (!numbers.contains(i)) {
                missingVal = i;
                break;
            }
        }
        
        // Replace the "?" with the found missing number.
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (square[i][j].equals("?")) {
                    square[i][j] = String.valueOf(missingVal);
                    break;
                }
            }
        }
        return missingVal;
    }

    /**
     * Solves the main puzzle by rearranging the squares.
     *
     * @param mat The larger matrix containing 4x4 squares.
     * @return The rearranged matrix with missing values filled in.
     */
    public String[][] solution(String[][] mat) {
        int n = mat[0].length / 4; // number of 4x4 squares
        List<SquareInfo> squares = new ArrayList<>();

        // 1. Extract each 4x4 square, find its missing value, and store the info.
        for (int k = 0; k < n; k++) {
            String[][] currentSquare = new String[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    currentSquare[i][j] = mat[i][k * 4 + j];
                }
            }
            int missingVal = findAndReplaceMissing(currentSquare);
            squares.add(new SquareInfo(k, missingVal, currentSquare));
        }

        // 2. Sort the squares based on their missing value.
        Collections.sort(squares);

        // 3. Reconstruct the large matrix from the sorted squares.
        String[][] resultMat = new String[4][4 * n];
        for (int k = 0; k < n; k++) {
            String[][] sortedSquare = squares.get(k).square;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    resultMat[i][k * 4 + j] = sortedSquare[i][j];
                }
            }
        }

        return resultMat;
    }

    /**
     * Main method to test the solution with an example.
     */
    public static void main(String[] args) {
        PuzzleSolver solver = new PuzzleSolver();

        // Example from the problem description
        String[][] exampleMat = {
            {"1", "2", "3", "4", "11", "5", "6", "10"},
            {"?", "5", "6", "10", "13", "16", "12", "15"},
            {"13", "16", "12", "15", "9", "7", "8", "14"},
            {"9", "7", "8", "14", "1", "2", "3", "4"}
        };
        
        System.out.println("Original Matrix:");
        printMatrix(exampleMat);

        String[][] result = solver.solution(exampleMat);

        System.out.println("\nRearranged Matrix:");
        printMatrix(result);
    }
    
    /**
     * A utility method to print a matrix for easy viewing.
     */
    private static void printMatrix(String[][] matrix) {
        for (String[] row : matrix) {
            for (String val : row) {
                System.out.print(String.format("%-4s", val));
            }
            System.out.println();
        }
    }
}
