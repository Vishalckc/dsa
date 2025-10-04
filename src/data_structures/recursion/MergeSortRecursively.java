package data_structures.recursion;

import java.util.Arrays;

public class MergeSortRecursively {
    public static void main(String[] args) {
        //int arr[] = {79, 68, 32, 41, 33, 29};
        int arr[] = {35, 0, 19, 86, 856, 75, 14, -1};

        int[] arr1= mergeSortRecursively(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr1));
    }

    private static int[] mergeSortRecursively(int[] arr, int start, int end) {
        if (end - start < 2) {
            return arr;
        }
        int mid = (end + start) / 2;
        mergeSortRecursively(arr, start, mid);
        mergeSortRecursively(arr, mid, end);
        return mergeLeftRightSubtrees(arr, start, mid, end);
    }

    private static int[] mergeLeftRightSubtrees(int[] arr, int start, int mid, int end) {
        if (arr[mid - 1] <= arr[mid]) return arr;
        int[] left = Arrays.copyOfRange(arr, start, mid);
        int[] right = Arrays.copyOfRange(arr, mid, end);
        int index = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        int[] mergedArr = new int[left.length + right.length];
        while (leftIndex < left.length || rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                mergedArr[index++] = left[leftIndex++];
            } else {
                mergedArr[index++] = right[rightIndex++];
            }
            if (leftIndex == left.length) {
                while (rightIndex < right.length) {
                    mergedArr[index++] = right[rightIndex++];
                }
            }
            if (rightIndex == right.length) {
                while (leftIndex < left.length) {
                    mergedArr[index++] = left[leftIndex++];
                }
            }
        }
        return mergedArr;
    }
}
