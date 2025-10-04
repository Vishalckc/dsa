package data_structures.misc;

public class KLargestElementInAnArray {
    public static void main(String[] args) {
        //int nums[] = {3, 2, 1, 5, 6, 4};
        int nums[] = {3,2,3,1,2,4,5,5,6};
        //int k = 2;
        int k = 4;
        KLargestElementInAnArray obj = new KLargestElementInAnArray();
        System.out.println(obj.findKthLargest(nums, k));
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) return Integer.MIN_VALUE;
        int largest[] = new int[k];
        for (int i = 0; i < nums.length; i++) {
            //Perform insertion sort in descending orders
            insertionSort(largest, nums[i]);
        }
        return largest[largest.length - 1];
    }

    private void insertionSort(int[] largest, int num) {
        for (int j = 0; j < largest.length; j++) {
            if (num >= largest[j]) {
                int old = largest[j];
                largest[j] = num;
                while (j < largest.length-1) {
                    largest[j + 1] = old;
                    ++j;
                }
                break;
            }
        }
    }
}



