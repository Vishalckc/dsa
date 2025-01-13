package data_structures.recursion;

import java.util.Arrays;

public class SelectionSortRecursive {
    public static void main(String[] args) {
        int arr[] = {30, 19, 38, 32, 459, 12, 55};
        selection(arr, arr.length, 0, 0);
        Arrays.stream(arr).forEach(System.out::println);
    }

    static void selection(int[] arr, int r, int c, int max) {
        if (r == 0) {
            return;
        }
        if (c < r) {
            if (arr[c] > arr[max]) {
                selection(arr, r, c+1, c);
            } else {
                selection(arr, r, c+1, max);
            }
        } else {
            int temp = arr[max];
            arr[max] = arr[r-1];
            arr[r-1] = temp;
            selection(arr, r-1, 0, 0);
        }
    }
}

