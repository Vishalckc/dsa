package data_structures.misc;

import java.util.ArrayList;
import java.util.List;
/*
* Given an integer array nums of unique elements, return all possible
subsets
 (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.
  Input: nums = [1,2,3]
  Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
* */

class AllPossibleSubsets {
    public static void main(String[] args) {
        int nums[] = {1, 2, 3};
        AllPossibleSubsets obj = new AllPossibleSubsets();
        List<List<Integer>> list = obj.subsets(nums);
        list.stream()
                .map(List::toString)
                .map(s -> String.join(",", s))
                .forEach(System.out::println);

    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();

        createSubset(nums, 0, res, subset);
        return res;
    }

    private void createSubset(int[] nums, int index, List<List<Integer>> res, List<Integer> subset) {
        if (index == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }

        subset.add(nums[index]);
        createSubset(nums, index + 1, res, subset);

        subset.remove(subset.size() - 1);
        createSubset(nums, index + 1, res, subset);
    }
}