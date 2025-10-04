package data_structures.repartners;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//Make class Threadsafe;
public class BankOriginal {
    private final Map<String, Integer> accounts = new ConcurrentHashMap<>();

    public void addAccount(String name, int balance) {
        accounts.put(name, balance);
    }

    public void transfer(String from, String to, int amount) {

        int fromBalance = accounts.get(from);
        int toBalance = accounts.get(to);

        if (fromBalance >= amount) {
            accounts.put(from, fromBalance - amount);
            accounts.put(to, toBalance + amount);
        }
    }

    public int getTotalBalance() {
        return accounts.values().stream().mapToInt(Integer::intValue).sum();
    }
}