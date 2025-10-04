package data_structures.repartners;/*
* Given an integer array nums and an integer k,
*  return true if there are two distinct indices i and j in
* the array such that nums[i] == nums[j] and abs(i - j) <= k.
Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
* */

import java.util.HashMap;
import java.util.Map;

public class Code1 {
    static int[][] nums = {{1, 2, 3, 1}, {1, 0, 1, 1}, {1, 2, 3, 1, 2, 3}};
    static int[] k = {3, 1, 2};

    public static void main(String[] args) {
        for (int i = 0; i < nums.length; i++) {
            Boolean b = hasDuplicate(nums[i], k[i]);
            System.out.println(b);
        }

    }

    private static Boolean hasDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])
                    && isDistanceLessThankEqualsToK(nums, k, map, i)) {
                return true;
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }

    private static boolean isDistanceLessThankEqualsToK(
            int[] nums, int k, Map<Integer, Integer> map, int i) {
        return Math.abs(map.get(nums[i]) - i) <= k;
    }

}
