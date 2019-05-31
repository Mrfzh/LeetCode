package dp;

public class Question174 {
    public static void main(String[] args) {
        int[][] dungeon = {{-2,-3,3}, {-5,-10,1}, {10,30,-5}};
        System.out.println(calculateMinimumHP(dungeon));
    }

    /**
     * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。
     * 我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
     *
     * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；
     * 其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，
     * 则表示骑士将增加健康点数）。为了尽快到达公主，骑士决定每次只向右或向下移动一步。
     *
     * 说明：
     * 1. 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
     * 骑士的健康点数没有上限。
     * 2. 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，
     * 包括骑士进入的左上角房间以及公主被监禁的右下角房间。
     *
     * 动态规划
     *
     * @param dungeon
     * @return
     */
    private static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;     //行数
        int n = (m == 0)? 0 : dungeon[0].length;    //列数
        //边界情况
        if (m < 1 || n < 1) {
            return 1;
        }

        int [][] dp = new int [m+1][n+1];   //dp[a][b]表示从dungeon[m-a][n-b]到终点所需的最小健康点数
        //初始情况
        dp[1][1] = (dungeon[m-1][n-1] >= 0)? 1 : 1-dungeon[m-1][n-1];
        for (int i = 2; i < m + 1; i++) {
            dp[i][1] = dp[i-1][1] - dungeon[m-i][n-1];
            if (dp[i][1] <= 0) {
                dp[i][1] = 1;
            }
        }
        for (int i = 2; i < n + 1; i++) {
            dp[1][i] = dp[1][i-1] - dungeon[m-1][n-i];
            if (dp[1][i] <= 0) {
                dp[1][i] = 1;
            }
        }

        //递推
        for (int i = 2; i < m + 1; i++) {
            for (int j = 2; j < n + 1; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) - dungeon[m-i][n-j];
                if (dp[i][j] <= 0) {
                    dp[i][j] = 1;
                }
            }
        }

        return dp[m][n];
    }
}
