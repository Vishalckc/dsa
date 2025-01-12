package data_structures.recursion;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BubbleSortRecursion {
    //static int[] arr = {9, 7, 5, 2, 1};
    static int[] arr = {101, 7, 93, 201, 89, 96};

    public static void main(String[] args) {
        //iterative(arr);
        recursive(arr, arr.length);
        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void recursive(int[] arr, int length) {
        if (length == 0)
            return;
        IntStream.range(0, length - 1).forEach(j ->
                {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
        );
        recursive(arr, length - 1);
    }

    static void iterative(int[] arr) {
        for (int i = arr.length; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
