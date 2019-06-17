package dp;

public class Question96 {
    public static void main(String[] args) {
        System.out.println(new Question96().numTrees(3));
        System.out.println(new Question96().numTrees(4));
    }

    /**
     * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
     *
     * 动态规划
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        int [] dp = new int[n+1];   //dp[a]表示a个数组成的二叉搜索树有多少种
        //初始条件
        dp[0] = dp [1] = 1;
        dp[2] = 2;
        //递推
        for (int i = 3; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= i; j++) {
                sum += dp[j-1] * dp[i-j];
            }
            dp[i] = sum;
        }

        return dp[n];
    }
}
