package data_structures.misc;

import java.util.*;

public class MinMeetingRooms {
    public static void main(String[] args) {
        int rooms = minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}});
        System.out.println(rooms);
    }

    public static int minMeetingRooms(int[][] intervals) {
        // null check
        if (intervals == null || intervals.length == 0) return 0;

        //Sort the intervals by start times
        Arrays.sort(intervals);

        //Create a min heap to store end times
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // Add the first end time
        minHeap.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.add(intervals[i][1]);
        }

        return minHeap.size();
    }
}
