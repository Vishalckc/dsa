package data_structures.misc;

import java.util.concurrent.*;
public class ECommerceOrderProcessing {
    public static void main(String[] args) {
        CompletableFuture<Void> orderValidation = CompletableFuture.runAsync(() -> {
            simulateDelay(1000);
            System.out.println("✅ Order validated");
        });
        CompletableFuture<Void> paymentProcessing = CompletableFuture.runAsync(() -> {
            simulateDelay(1500);
            System.out.println("💰 Payment processed");
        });
        CompletableFuture<Void> inventoryUpdate = CompletableFuture.runAsync(() -> {
            simulateDelay(1200);
            System.out.println("📦 Inventory updated");
        });
        CompletableFuture<Void> confirmationEmail = CompletableFuture.runAsync(() -> {
            simulateDelay(1500);
            System.out.println("📧 Confirmation email sent");
        });
        // Wait for all tasks to complete
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(orderValidation, paymentProcessing, inventoryUpdate, confirmationEmail);
        
        allTasks.join();
        System.out.println("🚀 Order successfully processed!");
    }
    private static void simulateDelay(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}