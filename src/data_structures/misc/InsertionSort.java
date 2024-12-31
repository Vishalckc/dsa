package data_structures.misc;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int arr[] = {20, 35, -15, 7, 55, 1, -22, 9, 33, 88};


        for (int i = 1; i < arr.length; i++) {
            int element = arr[i];
            System.out.println("Element to sort "+arr[i]);
            //int smallPos=i+1;
            int k;
            for (k = i; k > 0 && arr[k - 1] > element; k--) {
                arr[k] = arr[k - 1];
                System.out.println("\tShifting element by one spot to " +arr[k]);
            }
            System.out.println("\tAssigning element to position " + k);
            arr[k]=element;
        }
        System.out.println("--------PRINTING SORTED--------------");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
