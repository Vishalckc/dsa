package data_structures.recursion;

public class ArraySortDetection {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(checkArray(arr, 0));
    }

    private static boolean checkArray(int[] arr, int i) {
        if (i == arr.length - 1) return true;
        return arr[i] < arr[i + 1] && checkArray(arr, i + 1);
    }
}
