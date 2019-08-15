package dp;

public class Question279 {
    public static void main(String[] args) {
        System.out.println(new Question279().numSquares(12));
        System.out.println(new Question279().numSquares(13));
    }

    /**
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）
     * 使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     *
     * 动态规划
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];    // dp[a] 表示组成 a 的最少完全平方数个数
        for (int i = 1; i <= n; i++) {
            int min = i;
            for (int j = 1; i >= j*j; j++) {
                min = Math.min(min, dp[i-j*j] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }
}
