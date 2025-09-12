package lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.stream.Collectors;

public class MinHeapExample {


    public static void main(String[] args) {
        List<String> list = Arrays.asList("zebra", "apple", "banana", "cat", "dog", "elephant", "fox", "gorilla");
        int k = 3;

        // Create a Max-Heap to store the k smallest elements
        PriorityQueue<String> maxHeap = new PriorityQueue<>(k, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        for (String s : list) {
            maxHeap.offer(s); // Add to the heap
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Remove the largest element if size > k
            }
        }

        // The heap now contains the 3 smallest elements, but in no particular order.
        // We can either extract them one by one or put them into a new list.
        List<String> result = maxHeap.stream().sorted().collect(Collectors.toList());

        System.out.println(result); // Prints [apple, banana, cat]
    }
}

