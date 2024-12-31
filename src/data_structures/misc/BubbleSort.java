package data_structures.misc;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[]={35, 0, 19, 86, 85675, 14, -1};
        for(int i=arr.length-1;i>=0;i--)
        {
           // int max=0;
            for(int j=0;j<i;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        for(int i=0;i<arr.length;i++)
        {
            System.out.print(arr[i]+" ");
        }
    }
}
