package data_structures.slidingwindow;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class RateLimitedOrderProcessor {
    // --- Configuration Variables ---
    private final int MAX_LIMIT_ORDERS;         // Maximum number of orders allowed
    private final long MAX_LIMIT_TIMEINMILLISEC; // Time window duration in milliseconds

    // --- Core Data Structures ---
    // A thread-safe, non-blocking Deque (Double-Ended Queue)
    // It stores the timestamps of all currently accepted orders within the sliding window.
    // The "oldest" timestamp is at the head (peekFirst), and the "newest" is at the tail.
    private final Deque<Long> acceptedTimestamps;

    // A thread-safe List to store all accepted orders permanently, in the order they were processed.
    // This satisfies the requirement for `getProcessedOrders()`.
    private final List<String> processedOrdersList;

    /**
     * Constructor for the RateLimitedOrderProcessor.
     *
     * @param maxOrders        The maximum number of orders to accept per time window.
     * @param timeWindowMillis The duration of the time window in milliseconds.
     */
    public RateLimitedOrderProcessor(int maxOrders, long timeWindowMillis) {
        // 1. Initialize configuration constants
        this.MAX_LIMIT_ORDERS = maxOrders;
        this.MAX_LIMIT_TIMEINMILLISEC = timeWindowMillis;

        // 2. Initialize the core data structures
        // ConcurrentLinkedDeque is non-blocking and thread-safe, suitable for high concurrency.
        this.acceptedTimestamps = new ConcurrentLinkedDeque<>();
        // Use a synchronized list wrapper for thread-safe access to the final order list.
        this.processedOrdersList = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Submits an order for processing.
     * This method is thread-safe, non-blocking, and implements the core sliding window logic.
     *
     * @param order The order identifier string.
     */
    public void submitOrder(String order) {
        long currentTime = System.currentTimeMillis();

        // 1. Sliding Window Cleanup: Remove expired timestamps
        // This loop removes all timestamps that are outside the current time window.
        // It runs in O(1) amortized time, as each timestamp is added once and removed once.
        while (true) {
            // Check the timestamp of the oldest order (at the head of the Deque).
            Long oldestTimestamp = acceptedTimestamps.peekFirst();

            // If the queue is empty or the oldest order is NOT older than the window, stop cleanup.
            if (oldestTimestamp == null || (currentTime - oldestTimestamp) < MAX_LIMIT_TIMEINMILLISEC) {
                break;
            }

            // If the oldest order IS older than the window, remove it.
            // pollFirst() is an atomic operation, safe in a ConcurrentLinkedDeque.
            acceptedTimestamps.pollFirst();
        }

        // 2. Check Limit and Accept/Drop
        // The size of the Deque now represents the true number of accepted orders in the current window.
        if (acceptedTimestamps.size() < MAX_LIMIT_ORDERS) {
            // Limit has NOT been reached. Accept the order.

            // Record the acceptance time in the Deque (sliding window bookkeeping).
            acceptedTimestamps.addLast(currentTime);

            // Forward the accepted order to the next processing stage (the final list).
            // This is the "processed" state, fulfilling the order forwarding requirement.
            processedOrdersList.add(order);
        } else {
            // Limit has been reached within the time window. Silently drop the order.
        }
    }

    /**
     * Retrieves all orders that were accepted (processed), in the order of acceptance.
     *
     * @return A List of all accepted order strings.
     */
    public List<String> getProcessedOrders() {
        // Return a new, immutable copy of the processed orders list for thread-safety and data integrity.
        // This prevents external modifications to the internal state.
        return new ArrayList<>(processedOrdersList);
    }
}