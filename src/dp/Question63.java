package dp;

public class Question63 {
    public static void main(String[] args) {
        int[][] obstacleGrid = {{0,0,0,1,0}, {0,0,0,1,0}, {0,0,0,1,0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

    /**
     * 一个机器人位于一个 m x n 网格的左上角，机器人每次只能向下或者向右移动一步。
     * 机器人试图达到网格的右下角，现在考虑网格中有障碍物。（网格中的障碍物和空位置分别用 1 和 0 来表示。）
     * 那么从左上角到右下角将会有多少条不同的路径？
     *
     * 动态规划
     *
     * @param obstacleGrid
     * @return
     */
    private static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;    //行数
        int n = (m == 0)? 0 : obstacleGrid[0].length;   //列数
        //边界情况
        if (m < 1 || n < 1) {
            return 0;
        }
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }

        int [][] dp = new int[m+1][n+1];    //dp[a][b]代表从obstacleGrid[m-a][n-b]到终点的路径数量
        //初始条件
        boolean isFoundObstacle = false;
        for (int i = 1; i < m + 1; i++) {
            if (!isFoundObstacle && obstacleGrid[m-i][n-1] == 1) {
                isFoundObstacle = true;
            }
            dp[i][1] = isFoundObstacle? 0 : 1;
        }
        isFoundObstacle = false;
        for (int i = 1; i < n + 1; i++) {
            if (!isFoundObstacle && obstacleGrid[m-1][n-i] == 1) {
                isFoundObstacle = true;
            }
            dp[1][i] = isFoundObstacle? 0 : 1;
        }

        //递推
        for (int i = 2; i < m + 1; i++) {
            for (int j = 2; j < n + 1; j++) {
                dp[i][j] = (obstacleGrid[m-i][n-j] == 1)? 0 : (dp[i-1][j] + dp[i][j-1]);
            }
        }

        return dp[m][n];
    }
}
