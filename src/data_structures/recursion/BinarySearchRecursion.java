package data_structures.recursion;

import java.util.stream.IntStream;

public class BinarySearchRecursion {
    public static void main(String[] args) {
        int arr[] = {10, 19, 30, 87, 97, 101, 130, 450, 901};
        int target = 28;
        IntStream.range(0, arr.length).map(i -> binarySearch(arr, arr[i], 0, arr.length-1)).forEach(System.out::println);
        System.out.println(binarySearch(arr, target, 0, arr.length - 1));
    }

    private static int binarySearch(int[] arr, int target, int start, int end) {
        int mid = start + (end - start) / 2;
        if (start == end && arr[mid] != target) return -1;
        if (arr[mid] == target) return mid;
        else if (arr[mid] > target) return binarySearch(arr, target, start, mid);
        else return binarySearch(arr, target, mid + 1, end);
    }
}
