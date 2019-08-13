package three_pointer;

public class Question264 {
    public static void main(String[] args) {
        System.out.println(new Question264().nthUglyNumber(10));
        System.out.println(new Question264().nthUglyNumber(1));
        System.out.println(new Question264().nthUglyNumber(2));
        System.out.println(new Question264().nthUglyNumber(3));
    }

    /**
     * 编写一个程序，找出第 n 个丑数。
     *
     * 丑数就是只包含质因数 2, 3, 5 的正整数。
     *
     * 说明：
     * 1 是丑数。
     * n 不超过1690。
     *
     * dp + 三指针
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        // dp[a] 表示第 a+1 个丑数
        int[] dp = new int[n];
        dp[0] = 1;
        // 三指针：下一结果为当前指针指向的元素乘以 2,3,5
        int p2 = 0, p3 = 0, p5 = 0;
        // 递推
        for (int i = 1; i < n; i++) {
            int next = Math.min(dp[p2]*2, Math.min(dp[p3]*3, dp[p5]*5));
            // 对应指针后移一位
            if (dp[p2]*2 == next) p2++;
            if (dp[p3]*3 == next) p3++;
            if (dp[p5]*5 == next) p5++;
            dp[i] = next;
        }
        return dp[n-1];
    }
}
