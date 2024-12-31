package data_structures.misc;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {35, 0, 19, 86, 856, 75, 14, -1};
        // 75, 856, -1, 14

        mergeSort(arr, 0, arr.length);
        Arrays.stream(arr).mapToObj(Integer::toString).map(s-> s+" ").forEach(System.out::print);
    }

    //recursively divide the array into sub arrays
    public static void mergeSort(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
        if (end - start < 2) {
            return;
        }
        int mid = (start + end)/2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid, end);
        merge(arr, start, mid, end);
    }

    private static void merge(int[] arr, int start, int mid, int end) {

        if (arr[mid-1] <= arr[mid]) return;
        int temp[]= new int[end-start];
        int i = start, j = mid, tempindex = 0;
        while(i < mid && j < end) {
            temp[tempindex++] = arr[i] <= arr[j]? arr[i++]:arr[j++];
        }
        System.arraycopy(arr, i, arr, start+tempindex, mid-i);
        System.arraycopy(temp, 0, arr, start, tempindex);
        System.out.println("Printing merge");

       /* int n1 = mid - start + 1; // Left subarray size
        int n2 = end - mid;    // Right subarray size

        int[] firstarr = new int[n1];
        int[] secarr = new int[n2];
        for (int i = 0; i < n1; i++) {
            firstarr[i] = arr[start + i];
        }
        for (int i = 0; i < n2; i++) {
            secarr[i] = arr[mid  + i];
        }
        int i = 0, j = 0, k = start; // Initial indexes
        while (i < n1 && j < n2) {
            if (firstarr[i] <= secarr[j]) {
                arr[k] = firstarr[i];
                i++;
            } else {
                arr[k] = secarr[j];
                j++;
            }
            k++;
            while (i < n1) {
                arr[k] = firstarr[i];
                i++;
                k++;
            }

            // Copy remaining elements of rightArray, if any
            while (j < n2) {
                arr[k] = secarr[j];
                j++;
                k++;
            }
        }*/

    }
}
