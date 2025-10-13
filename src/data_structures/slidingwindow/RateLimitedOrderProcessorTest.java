package data_structures.slidingwindow;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class RateLimitedOrderProcessorTest {

    @Test
    public void testSingleThreadedDropBehavior() throws InterruptedException {
        RateLimitedOrderProcessor processor = new RateLimitedOrderProcessor(3, 1000); // 3 per second

        processor.submitOrder("order1");
        processor.submitOrder("order2");
        processor.submitOrder("order3");
        processor.submitOrder("order4");  // Should be dropped

        List<String> results = processor.getProcessedOrders();
        assertEquals(3, results.size(), "Only the first 3 orders should be accepted within the time window");
        assertFalse(results.contains("order4"), "order4 should have been dropped");
    }

    @Test
    public void testSlidingWindowRejectsBurstAcrossFixedWindowBoundary() throws InterruptedException {
        RateLimitedOrderProcessor processor = new RateLimitedOrderProcessor(3, 1000);

        // Let’s start clean
        long base = System.currentTimeMillis();

        // Step 1: Submit 3 orders leading up to the 1000ms mark
        while (System.currentTimeMillis() - base < 900) {
            Thread.sleep(10); // wait until close to 1000ms mark
        }

        processor.submitOrder("order1"); // ~t = 900ms
        Thread.sleep(30);
        processor.submitOrder("order2"); // ~t = 930ms
        Thread.sleep(30);
        processor.submitOrder("order3"); // ~t = 960ms

        // Step 2: Wait a bit to cross the 1000ms mark, but not enough for old orders to expire
        Thread.sleep(100); // Now ~t = 1060ms

        processor.submitOrder("order4"); // this should be dropped
        processor.submitOrder("order5"); // this should be dropped
        processor.submitOrder("order6"); // this should be dropped

        List<String> results = processor.getProcessedOrders();

        assertEquals(3, results.size(), "Only 3 orders should be accepted within any sliding 1000ms window");
        assertTrue(results.contains("order1"));
        assertTrue(results.contains("order2"));
        assertTrue(results.contains("order3"));
        assertFalse(results.contains("order4"));
        assertFalse(results.contains("order5"));
        assertFalse(results.contains("order6"));
    }

    @Test
    public void testOrdersAcceptedAfterWindowSlides() throws InterruptedException {
        RateLimitedOrderProcessor processor = new RateLimitedOrderProcessor(3, 1000);

        // Fill the window
        processor.submitOrder("order1");
        processor.submitOrder("order2");
        processor.submitOrder("order3");

        // This should be dropped (window full)
        processor.submitOrder("order4");

        // Wait for the first order to expire from the window
        Thread.sleep(1100);

        // This should now be accepted since one slot has freed up
        processor.submitOrder("order5");

        List<String> results = processor.getProcessedOrders();

        assertEquals(4, results.size(), "Should have 4 orders after window slides");
        assertTrue(results.contains("order1"));
        assertTrue(results.contains("order2"));
        assertTrue(results.contains("order3"));
        assertFalse(results.contains("order4"));
        assertTrue(results.contains("order5"), "order5 should be accepted after window slides");
    }

    @Test
    public void testContinuousSlidingWindowBehavior() throws InterruptedException {
        RateLimitedOrderProcessor processor = new RateLimitedOrderProcessor(3, 1000);

        // Initial burst of orders
        processor.submitOrder("order1"); // t=0
        Thread.sleep(400);
        processor.submitOrder("order2"); // t=400
        Thread.sleep(400);
        processor.submitOrder("order3"); // t=800

        // This should be dropped (window full)
        processor.submitOrder("order4"); // t=800

        // Wait for order1 to expire (should expire at t=1000)
        Thread.sleep(300); // Now at t=1100

        // This should be accepted (order1 should be outside the window)
        processor.submitOrder("order5"); // t=1100

        // Wait for order2 to expire (should expire at t=1400)
        Thread.sleep(400); // Now at t=1500

        // This should be accepted (order2 should be outside the window)
        processor.submitOrder("order6"); // t=1500

        // Try to overload again
        processor.submitOrder("order7"); // t=1500
        processor.submitOrder("order8"); // t=1500 - should be dropped

        List<String> results = processor.getProcessedOrders();

        assertEquals(5, results.size());
        assertTrue(results.contains("order1"));
        assertTrue(results.contains("order2"));
        assertTrue(results.contains("order3"));
        assertFalse(results.contains("order4"));
        assertTrue(results.contains("order5"));
        assertTrue(results.contains("order6"));
        assertFalse(results.contains("order8"));
    }

    @Test
    public void testMultithreadedRateLimitingDrops() throws InterruptedException {
        int maxOrders = 10;
        long timeWindowMillis = 1000;
        RateLimitedOrderProcessor processor = new RateLimitedOrderProcessor(maxOrders, timeWindowMillis);
        ExecutorService executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 50; i++) {
            final int orderId = i;
            executor.submit(() -> processor.submitOrder("order-" + orderId));
        }

        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);

        List<String> results = processor.getProcessedOrders();
        assertTrue(results.size() <= maxOrders,
                "Should not process more than " + maxOrders + " orders within the time window, but got " + results.size());
    }

    @Test
    public void testConcurrentOrderProcessing() throws InterruptedException {
        // Allow 100 orders per second
        final int MAX_ORDERS_PER_SECOND = 100;
        final int TOTAL_ORDERS = 1000;
        final int NUM_THREADS = 20;
        final int TEST_DURATION_MS = 5000; // 5 seconds test duration

        RateLimitedOrderProcessor processor = new RateLimitedOrderProcessor(MAX_ORDERS_PER_SECOND, 1000);

        // Counter for successful order submissions
        final AtomicInteger submittedCount = new AtomicInteger(0);
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(NUM_THREADS);

        // Create and start worker threads
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            executor.submit(() -> {
                try {
                    startLatch.await(); // Wait for all threads to be ready

                    // Keep submitting orders until we've reached the test duration
                    long endTime = System.currentTimeMillis() + TEST_DURATION_MS;
                    while (System.currentTimeMillis() < endTime) {
                        String orderId = "order-" + threadId + "-" + System.nanoTime();
                        processor.submitOrder(orderId);
                        submittedCount.incrementAndGet();

                        // Add a small random delay to simulate real-world conditions
                        Thread.sleep(ThreadLocalRandom.current().nextInt(1, 10));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    endLatch.countDown();
                }
            });
        }

        // Start all threads simultaneously
        startLatch.countDown();

        // Wait for all threads to finish or timeout
        assertTrue(endLatch.await(TEST_DURATION_MS + 2000, TimeUnit.MILLISECONDS),
                "Test timed out - possible deadlock detected");

        // Shutdown the executor
        executor.shutdown();

        // Get the actual processed orders
        List<String> processedOrders = processor.getProcessedOrders();

        // Verify rate limiting was enforced
        assertTrue(processedOrders.size() <= (MAX_ORDERS_PER_SECOND * (TEST_DURATION_MS / 1000 + 1)),
                "Processed orders should not exceed the rate limit");

        // Verify no duplicates were processed (thread safety check)
        long uniqueOrders = processedOrders.stream().distinct().count();
        assertEquals(processedOrders.size(), uniqueOrders,
                "Duplicate orders detected - possible thread safety issue");

        System.out.println("Stress test results:");
        System.out.println("- Attempted orders: " + submittedCount.get());
        System.out.println("- Processed orders: " + processedOrders.size());
        System.out.println("- Rate limit: " + MAX_ORDERS_PER_SECOND + " orders/second");
    }
}
