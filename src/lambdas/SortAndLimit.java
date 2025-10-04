package lambdas;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Sorting and Limiting: Given a list of strings, return the first 3 strings in alphabetical order.
//
//Example: List.of("zebra", "apple", "banana", "cat") should return List.of("apple", "banana", "cat").
public class SortAndLimit {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("zebra", "apple", "banana", "cat");
        List<String> newlist = list.stream().sorted()
                .limit(3)
                .collect(Collectors.toList());

        newlist.forEach(System.out::println);
    }

}
