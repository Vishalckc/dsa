package data_structures.misc;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        //int arr[] = {35, 0, 19, 86, 856, 75, 14, -1};
        int arr[] = {79, 68, 32, 41, 33, 29};
        mergeSort(arr, 0, arr.length);
        Arrays.stream(arr).mapToObj(Integer::toString).map(s-> s+" ").forEach(System.out::print);
    }

    public static void mergeSort(int[] arr, int start, int end) {
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

    }
}
