package data_structures.recursion;

public class MergeSortRecursively {
    public static void main(String[] args) {
        int arr[] = {79, 68, 32, 41, 33, 29};
        mergeSortRecursively(arr, 0, arr.length - 1);
    }

    private static int[] mergeSortRecursively(int[] arr, int start, int end) {
        if (start >= end)
            return arr[start];
        int mid = (start + end) / 2;
        int left[] = mergeSortRecursively(arr, start, mid);
        int right[] = mergeSortRecursively(arr, mid + 1, end);
        return mergeLeftRightSubtrees(left, right);
    }

    private static int[] mergeLeftRightSubtrees(int[] left, int[] right) {
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
                    mergedArr[++index] = right[++rightIndex];
                }
            }
            if (rightIndex == right.length) {
                while (leftIndex < left.length) {
                    mergedArr[++index] = left[++leftIndex];
                }
            }
        }
        return mergedArr;
    }
}
