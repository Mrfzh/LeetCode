package dp;

public class Question309 {
    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2,6,7,1,2,3,0,2,1,2,3,0,2};
        System.out.println(new Question309().maxProfit(prices));
    }

    /**
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     *
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 1. 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 2. 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     *
     * dp
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        // buy[a] 表示第 a 天可以买入时，所得的最大利润
        int[] buy = new int[prices.length];
        buy[prices.length-1] = 0;
        // sell[a] 表示第 a 天可以卖出时，所得的最大利润
        int[] sell = new int[prices.length];
        sell[prices.length-1] = prices[prices.length-1];

        for (int i = prices.length-2; i >= 0; i--) {
            // 当前第 i 天买入可获得的最大利润
            int currProfit = Math.max(0, sell[i+1] - prices[i]);
            buy[i] = Math.max(currProfit, buy[i+1]);
            // 当前第 i 天卖出可获得的最大利润
            currProfit = prices[i];
            // 加上冷冻期结束后继续买入可获得的最大利润
            if (i + 2 < prices.length) {
                currProfit += buy[i+2];
            }
            sell[i] = Math.max(currProfit, sell[i+1]);
        }

        return buy[0];
    }
}
