package dp;

public class Question64 {
    public static void main(String[] args) {
        int[][] grid = {{1,3,1}, {1,5,1}, {4,2,1}};
        System.out.println(minPathSum(grid));
    }

    /**
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小，
     * 并返回该数组总和。
     *
     * 说明：每次只能向下或者向右移动一步。
     *
     * 动态规划
     *
     * @param grid
     * @return
     */
    private static int minPathSum(int[][] grid) {
        int m = grid.length;    //行数
        int n = (m == 0)? 0 : grid[0].length;   //列数
        //边界情况
        if (m < 1 || n < 1) {
            return 0;
        }

        int [][] dp = new int[m+1][n+1];    //dp[a][b]代表从grid[m-a][n-b]到终点最短路径长度
        //初始条件
        int sum = 0;
        for (int i = 1; i < m + 1; i++) {
            sum += grid[m-i][n-1];
            dp[i][1] = sum;
        }
        sum = 0;
        for (int i = 1; i < n + 1; i++) {
            sum += grid[m-1][n-i];
            dp[1][i] = sum;
        }

        //递推
        for (int i = 2; i < m + 1; i++) {
            for (int j = 2; j < n + 1; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[m-i][n-j];
            }
        }

        return dp[m][n];
    }
}
