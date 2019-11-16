package dfs;

public class Question695 {
    public static void main(String[] args) {
        int[][] grid = {{1,1,0,0,0}, {1,1,0,0,0}, {0,0,0,1,1}, {0,0,0,1,1}};
        System.out.println(new Question695().maxAreaOfIsland(grid));
    }

    /**
     * 给定一个包含了一些 0 和 1的非空二维数组 grid ,
     * 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。
     * 你可以假设二维矩阵的四个边缘都被水包围着。
     *
     * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
     *
     * dfs
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int res = 0;
        boolean[][] hasFound = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!hasFound[i][j]) {
                    res = Math.max(res, dfs(i, j, row, col, hasFound, grid));
                }
            }
        }

        return res;
    }

    /**
     * 寻找从 grid[i][j] 出发，所能找到的最大陆地面积
     * row 和 col 分别为 grid 的行数和列数，hasFound 表示该地方是否查找过
     */
    private int dfs(int i, int j, int row, int col,
                    boolean[][] hasFound, int[][] grid) {
        if (i < 0 || i >= row || j < 0 || j >= col || hasFound[i][j] || grid[i][j] == 0) {
            return 0;
        }
        hasFound[i][j] = true;
        return 1 + dfs(i - 1, j, row, col, hasFound, grid)
                + dfs(i + 1, j, row, col, hasFound, grid)
                + dfs(i, j - 1, row, col, hasFound, grid)
                + dfs(i, j + 1, row, col, hasFound, grid);
    }
}
