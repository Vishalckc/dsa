package data_structures.repartners;
//To make the transfer thread safe, atomic and ensure consistent lock ordering to prevent deadlocks
//maximize the use of concurrent collections and transactions
//Original question is given in a separate class
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class Bank {
    private final Map<String, Integer> accounts = new ConcurrentHashMap<>();

    public void addAccount(String name, int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        accounts.put(Objects.requireNonNull(name), balance);
    }

    public boolean transfer(String from, String to, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        if (from.equals(to)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        // Ensure consistent lock ordering to prevent deadlocks
        String firstLock = from.compareTo(to) < 0 ? from : to;
        String secondLock = firstLock.equals(from) ? to : from;

        synchronized (firstLock.intern()) {
            synchronized (secondLock.intern()) {
                Integer fromBalance = accounts.get(from);
                if (fromBalance == null || fromBalance < amount) {
                    return false;
                }
                
                // Perform the transfer atomically
                accounts.compute(from, (k, v) -> v - amount);
                accounts.merge(to, amount, Integer::sum);
                return true;
            }
        }
    }

    public int getBalance(String account) {
        return accounts.getOrDefault(account, 0);
    }

    public int getTotalBalance() {
        return accounts.values().stream().mapToInt(Integer::intValue).sum();
    }
}