package dp;

public class Question714 {
    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        System.out.println(new Question714().maxProfit(prices, 2));
    }

    /**
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；
     * 非负整数 fee 代表了交易股票的手续费用。
     *
     * 你可以无限次地完成交易，但是你每次交易都需要付手续费。
     * 如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     *
     * dp
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        // has[a] 表示第 a 天持有股票所得的最大利润
        int[] has = new int[prices.length];
        has[0] = -prices[0];
        // no[a] 表示第 a 天不持有股票所得的最大利润
        int[] no = new int[prices.length];

        for (int i = 1; i < prices.length; i++) {
            has[i] = Math.max(no[i-1] - prices[i], has[i-1]);
            no[i] = Math.max(has[i-1] - fee + prices[i], no[i-1]);
        }

        return no[prices.length-1];
    }

    /**
     * 优化空间，因为每次只需用到上一个数，所以实际只需 O(1) 空间即可
     */
    public int maxProfit_better(int[] prices, int fee) {
        int has = -prices[0];
        int no = 0;

        for (int i = 1; i < prices.length; i++) {
            has = Math.max(no - prices[i], has);
            no = Math.max(has - fee + prices[i], no);
        }

        return no;
    }
}
