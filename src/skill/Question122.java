package skill;

public class Question122 {
    public static void main(String[] args) {
        int[] prices = {7,6,4,3,1};
        System.out.println(new Question122().maxProfit_better(prices));
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 动态规划（时间复杂度太高，是我想复杂了）
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }

        int [] dp = new int[n+1];   //dp[a]代表倒数a个元素的最大利润
        dp[0] = dp[1] = 0;
        dp[2] = (prices[n-1] - prices[n-2]) <= 0 ? 0 : (prices[n-1] - prices[n-2]);
        for (int i = 3; i < n + 1; i++) {
            //假如倒数第i天买入
            int max = 0;    //倒数第i天买入的最大利润
            for (int j = i-1; j >= 1; j--) {
                int curr = prices[n-j] - prices[n-i] + dp[j-1];
                if (curr > max) {
                    max = curr;
                }
            }
            //取第i天买入和不买入的利润较大值
            dp[i] = Math.max(max, dp[i-1]);
        }

        return dp[n];
    }

    /**
     * 优化后的算法
     * 思路：只要今天比前一天的价格高就卖出（题目的意思是可以今天卖出后，今天再买入的；
     * 而如果明天的价格比今天的高，那今天就不买入了）
     */
    public int maxProfit_better(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < n; i++) {
            if ((prices[i] - prices[i-1]) > 0) {
                res += prices[i] - prices[i-1];
            }
        }

        return res;
    }
}
