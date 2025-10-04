package data_structures.misc;

import java.util.*;
import java.util.stream.Collectors;

/*
 * 49 Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * */
public class GroupAnagrams {
    public static void main(String[] args) {

        String[] strs = new String[]{"cab", "tin", "pew", "duh", "may", "ill", "buy", "bar", "max", "doc"};
        List<List<String>> list = groupAnagrams(strs);
        list.stream()
                .map(List::toString)
                .map(s -> String.join(",", s))
                .forEach(System.out::println);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = new char[26];
            for (char c : s.toCharArray()) ca[c - 'a']++;
            String keyStr = String.valueOf(ca);
            System.out.printf("keyStr: %s\n", keyStr);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }


}
