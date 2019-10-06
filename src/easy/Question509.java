package easy;

public class Question509 {
    public static void main(String[] args) {
        System.out.println(new Question509().fib(4));
    }

    /**
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。
     * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     *
     * 给定 N，计算 F(N)。0 ≤ N ≤ 30
     *
     * @param N
     * @return
     */
    public int fib(int N) {
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        }

        // dp[a] 表示 F(a) 的值
        int[] dp = new int[N+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[N];
    }
}
