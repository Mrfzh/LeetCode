package dfs;

public class Question200 {
    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        System.out.println(new Question200().numIslands(grid));
    }

    private int row;  // 行数
    private int col;  // 列数

    /**
     * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
     * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
     * 你可以假设网格的四个边均被水包围。
     *
     * dfs
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        row = grid.length;  // 行数
        col = (row == 0) ? 0 : grid[0].length;  // 列数
        boolean[][] found = new boolean[row][col];
        int res = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1' && !found[i][j]) {
                    res++;
                    find(found, grid, i, j);
                }
            }
        }

        return res;
    }

    /**
     * 从 grid[i][j] 开始，向四周遍历，直到为 0 或 为已找过的 1 才停止
     */
    private void find(boolean[][] found, char[][] grid, int i, int j) {
        found[i][j] = true;
        if (i > 0 && grid[i-1][j] == '1' && !found[i-1][j]) {
            find(found, grid, i-1, j);
        }
        if (j > 0 && grid[i][j-1] == '1' && !found[i][j-1]) {
            find(found, grid, i, j-1);
        }
        if (i < row-1 && grid[i+1][j] == '1' && !found[i+1][j]) {
            find(found, grid, i+1, j);
        }
        if (j < col-1 && grid[i][j+1] == '1' && !found[i][j+1]) {
            find(found, grid, i, j+1);
        }
    }
}
