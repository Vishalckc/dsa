package data_structures.misc;

import java.util.*;

public class MinimumCoinsSumProblem {
    static int[] dp;

    public static void main(String[] args) {
        int[] coins = {1, 4, 5};
        int goal = 13;
        //int coinCount = minCount(goal, coins);
        //System.out.println(coinCount);

        System.out.println(minCoinsUsingDynamicProgramming(coins, goal));
    }

    private static int minCount(int amount, int[] coins) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        int minCoins = Integer.MAX_VALUE;
        for (Integer coin : coins) {
            int remAmt = minCount(amount - coin, coins);
            if (remAmt != Integer.MAX_VALUE) {
                minCoins = Math.min(minCoins, remAmt + 1);
            }
        }
        return minCoins;
    }


    public static int minCoinsUsingDynamicProgramming(int[] coins, int amount) {

        dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        int ans = coinCount(coins, amount);
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    static int coinCount(int[] coins, int amount) {

        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }

        if (dp[amount] != -1) {
            return dp[amount];
        }

        int minCoins = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int ans = coinCount(coins, amount - coins[i]);

            if (ans != Integer.MAX_VALUE) {

                //we have returned 0 in ans, so now we are updating the ans count
                //hence 1 + ans
                minCoins = Math.min(minCoins, 1 + ans);
            }
        }
        System.out.println("Amount " + amount + "\nmin Coins for this amount " + minCoins);
        return dp[amount] = minCoins;
    }
}
