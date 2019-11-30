package easy;

public class Question746 {
    public static void main(String[] args) {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(new Question746().minCostClimbingStairs(cost));
    }

    /**
     * 数组的每个索引做为一个阶梯，第i个阶梯对应着一个非负数的体力花费值cost[i](索引从0开始)。
     * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
     *
     * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
     *
     * 注意：
     * cost 的长度将会在 [2, 1000]。
     * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        // dp[a] 表示爬到第 a 层楼梯所需的最小体力
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]);
        }

        return Math.min(dp[cost.length-1], dp[cost.length-2]);
    }
}
