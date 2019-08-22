package dp;

import java.util.Arrays;

public class Question322 {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(new Question322().coinChange(coins, 11));
    }

    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。
     * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     * dp
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int min = coins[0];
        if (min > amount) {
            return -1;
        }

        // dp[a] 表示凑成金额 a 所需的最少的硬币个数，值为 -1 表示没有和为 a 的组合
        int[] dp = new int[amount+1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int minCoins = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin < 0) {
                    break;
                }
                if (dp[i - coin] == -1) {
                    continue;
                }
                minCoins = Math.min(minCoins, dp[i - coin]+1);
            }
            dp[i] = (minCoins == Integer.MAX_VALUE)? -1 : minCoins;
        }

        return dp[amount];
    }
}
