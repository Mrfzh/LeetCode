package dp;

public class Question62 {
    public static void main(String[] args) {
        System.out.println(uniquePaths(7, 3));
    }

    /**
     * 一个机器人位于一个 m x n 网格的左上角，机器人每次只能向下或者向右移动一步。
     * 机器人试图达到网格的右下角，问总共有多少条不同的路径？
     *
     * 动态规划
     *
     * @param m 列数
     * @param n 行数
     * @return
     */
    private static int uniquePaths(int m, int n) {
        //边界判断
        if (m <= 0 || n <= 0) {
            return 0;
        } else if (m == 1 || n == 1) {
            return 1;
        }

        int [][] dp = new int[m+1][n+1];    //dp[a][b]代表a x b的网格有多少条路径
        //初始条件
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }
        for (int i = 0; i < n + 1; i++) {
            dp[0][i] = 0;
            dp[1][i] = 1;
        }
        //递推
        for (int i = 2; i < m + 1; i++) {
            for (int j = 2; j < n + 1; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m][n];
    }
}
