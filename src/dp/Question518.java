package dp;

import java.util.Arrays;

public class Question518 {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(new Question518().change(5, coins));
    }

    /**
     * 给定不同面额的硬币和一个总金额。写
     * 出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     *
     * 你可以假设：
     * 0 <= amount (总金额) <= 5000
     * 1 <= coin (硬币面额) <= 5000
     * 硬币种类不超过 500 种
     * 结果符合 32 位符号整数
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        // 特殊情况
        if (amount == 0) {
            return 1;
        }
        if (coins.length == 0) {
            return 0;
        }
        // 先对硬币面额排序
        Arrays.sort(coins);
        if (amount < coins[0]) {
            return 0;
        }

        // dp[a] 表示能够凑成总金额为 a 的硬币组合数
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int coin : coins) {
            // 由低到高递推，是因为硬币可以重复使用
            // 大的依赖小的，小的之前可能已经选过该硬币了，但大的还是可以继续选该硬币
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }
}
