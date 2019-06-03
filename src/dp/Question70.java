package dp;

public class Question70 {
    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     *
     * 动态规划
     *
     * @param n
     * @return
     */
    private static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        int [] dp = new int [n+1];  //dp[a]表示当有a阶楼梯时，有多少种方法可以登上楼顶。

        //初始
        dp[1] = 1;
        dp[2] = 2;

        //递推
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}
