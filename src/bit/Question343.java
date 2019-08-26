package bit;

public class Question343 {
    public static void main(String[] args) {
        System.out.println(new Question343().integerBreak(10));
    }

    /**
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。
     * 返回你可以获得的最大乘积。
     *
     * 说明: 你可以假设 n 不小于 2 且不大于 58。
     *
     * dp
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }

        // dp[a] 表示 a 所能表示的最大值（不拆分的值或拆分后的乘积）
        int[] dp = new int[n+1];
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            int max = i;
            for (int j = 2; j <= i/2; j++) {
                max = Math.max(max, dp[j]*dp[i-j]);
            }
            dp[i] = max;
        }

        return dp[n];
    }
}
