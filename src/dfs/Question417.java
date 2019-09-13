package dfs;

import java.util.ArrayList;
import java.util.List;

public class Question417 {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,2,3,5},{3,2,3,4,4},
                {2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(new Question417().pacificAtlantic(matrix));
    }

    /**
     * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。
     * “太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
     *
     * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
     *
     * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
     *
     * 提示：
     * 1. 输出坐标的顺序不重要
     * 2. m 和 n 都小于150
     *
     * dfs
     *
     * @param matrix
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        int row = matrix.length;    // 行数
        int col = matrix[0].length; // 列数
        // toPacific[a][b] 表示 matrix[a][b] 是否可以流向太平洋
        boolean[][] toPacific = new boolean[row][col];
        // toAtlantic[a][b] 表示 matrix[a][b] 是否可以流向大西洋
        boolean[][] toAtlantic = new boolean[row][col];

        // 寻找各自所能到达的点
        for (int i = 0; i < row; i++) {
            dfs(matrix, i, 0, matrix[i][0], toPacific);
            dfs(matrix, i, col-1, matrix[i][col-1], toAtlantic);
        }
        for (int i = 0; i < col; i++) {
            dfs(matrix, 0, i, matrix[0][i], toPacific);
            dfs(matrix, row-1, i, matrix[row-1][i], toAtlantic);
        }

        List<List<Integer>> res = new ArrayList<>();
        // 遍历矩阵
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (toPacific[i][j] && toAtlantic[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
            }
        }

        return res;
    }

    /**
     * 从 matrix[x][y] 开始寻找下一个点，last 表示上一个点的值
     * matrix[x][y] 必须大于等于 last
     */
    private void dfs(int[][] matrix, int x, int y, int last, boolean[][] visited) {
        if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length
            || visited[x][y] || matrix[x][y] < last) {
            return;
        }
        // 表示可以到达该点
        visited[x][y] = true;
        // 寻找下一个点
        dfs(matrix, x, y-1, matrix[x][y], visited);
        dfs(matrix, x, y+1, matrix[x][y], visited);
        dfs(matrix, x-1, y, matrix[x][y], visited);
        dfs(matrix, x+1, y, matrix[x][y], visited);
    }
}
