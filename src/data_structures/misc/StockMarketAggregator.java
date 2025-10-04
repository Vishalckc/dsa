package data_structures.misc;

/*
* A financial application retrieves stock prices from three different APIs
* and needs to compute the average stock price.
* If any API fails, the system should return a default value for that source.
* How would you implement this using CompletableFuture
* */
import java.util.concurrent.*;
import java.util.Random;
public class StockMarketAggregator {
    public static void main(String[] args) {
        CompletableFuture<Double> api1 = fetchStockPrice("API-1");
        CompletableFuture<Double> api2 = fetchStockPrice("API-2");
        CompletableFuture<Double> api3 = fetchStockPrice("API-3");
        // Compute the average stock price once all APIs respond
        CompletableFuture<Double> averagePrice = api1
                .thenCombine(api2, Double::sum)
                .thenCombine(api3, (sum, price) -> (sum + price) / 3);
        System.out.println("📈 Final Stock Price: $" + averagePrice.join());
    }
    private static CompletableFuture<Double> fetchStockPrice(String api) {
        return CompletableFuture.supplyAsync(() -> {

            simulateDelay(new Random().nextInt(2000) + 500);
            if (new Random().nextBoolean()) {  // Simulating failure
                throw new RuntimeException(api + " failed!");
            }
            double price = 100 + new Random().nextDouble() * 10;
            System.out.println(api + " returned $" + price);

            return price;
        }).exceptionally(ex -> {
            System.err.println(ex.getMessage());
            return 100.0;  // Default fallback price
        });
    }
    private static void simulateDelay(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}