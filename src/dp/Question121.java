package dp;

public class Question121 {
    public static void main(String[] args) {
        int [] prices = {7,6,4,3,1};
        System.out.println(new Question121().maxProfit(prices));
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     * 注意你不能在买入股票前卖出股票。
     *
     * 动态规划
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }

        int [] dp = new int[n];     //dp[a]代表倒数a个元素的最大值
        dp[1] = prices[n-1];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(prices[n-i], dp[i-1]);
        }

        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            if ((dp[n-i-1] - prices[i]) > max) {
                max = dp[n-i-1] - prices[i];
            }
        }

        return max;
    }
}
