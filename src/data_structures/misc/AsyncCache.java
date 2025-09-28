package data_structures.misc;
/*
* SCENARIO: Asynchronous Caching with Auto-Refresh
A high-traffic application uses an in-memory cache for frequently accessed data (
* e.g., product details). The cache should:
* Fetch fresh data from the database only when stale.
* Auto-refresh data every 10 minutes asynchronously without blocking requests.
* How would you implement this using CompletableFuture?"_
* PuzzleSolver:
* The first request fetches from DB and caches it.
* Future requests use the cached version.
* A background task auto-refreshes every 10 minutes.
* */

import java.util.Random;
import java.util.concurrent.*;

public class AsyncCache {
    private static final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static CompletableFuture<String> getData(String key) {
        return CompletableFuture.supplyAsync(() -> {
            return cache.computeIfAbsent(key, AsyncCache::fetchFromDatabase);
        });
    }

    private static String fetchFromDatabase(String key) {
        simulateDelay(2000);
        Random rand = new Random();
        String string = "Data for " + key + " (Fetched from DB)" + rand.nextInt(100);
        System.out.println(string);
        return string;
    }

    public static void scheduleAutoRefresh() {
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("🔄 Auto-refreshing cache...");
            cache.replaceAll((k, v) -> fetchFromDatabase(k));
        }, 0, 5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        scheduleAutoRefresh();
        getData("product_123").thenAccept(System.out::println);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        getData("product_123").thenAccept(System.out::println);  // Uses cache
    }

    private static void simulateDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
