package lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Given a list of strings, count how many of them start with the letter 'A' (case-insensitive).
//
//Example: List.of("Apple", "Banana", "apricot", "Cherry") should return 2.
public class FindMatchingName {
    static List<String> fruits = Arrays.asList("Apple", "Banana", "apricot", "Cherry");

    public static void main(String[] args) {
        long size = fruits.stream()
                .filter(s -> s.startsWith("A") || s.startsWith("a"))
                .count();
        System.out.println(size);
    }

}
