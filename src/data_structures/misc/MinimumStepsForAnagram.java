package data_structures.misc;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/*
* You are given two strings of the same length s and t.
*  In one step you can choose any character of t and replace it with another character.
* Return the minimum number of steps to make t an anagram of s.
An Anagram of a string is a string that contains the same characters
*  with a different (or the same) ordering.
* Input: s = "bab", t = "aba" Output: 1
* */
public class MinimumStepsForAnagram {
    public static void main(String[] args) {
        Solution s = new Solution();
        int steps = s.minSteps("bab", "aba");
        System.out.println(steps);
    }
}

class Solution {
    public int minSteps(String s, String t) {
        int[] charCount = new int[26];
        // Count frequency of characters in string s
        for (char c : s.toCharArray()) {
            // updates values of a-z under indexes 0-25 respectively
            charCount[c - 'a']++;
        }
        // Decrement count for characters present in t
        for (char c : t.toCharArray()) {
            if (charCount[c - 'a'] > 0) {
                charCount[c - 'a']--;
            }
        }
        // Sum up the remaining counts which represent the differences
        int steps = 0;
        for (int count : charCount) {
            steps += count;
        }
        return steps;
    }
}
