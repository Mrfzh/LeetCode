package dp;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Question221 {
    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'}, {'1','0','1','1','1'},
                {'1','1','1','1','1'}, {'1','0','1','1','0'}};
        System.out.println(new Question221().maximalSquare_2_space_better(matrix));
    }

    private int row;
    private int col;

    /**
     * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
     *
     * 动态规划
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        row = matrix.length;    // 行数
        col = row == 0 ? 0 : matrix[0].length;  // 列数
        if (row == 0 || col == 0) {
            return 0;
        }

        List<Pair<Integer, Integer>> list = new ArrayList<>();
        // 先查找为 1 的点
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    Pair<Integer, Integer> pair = new Pair<>(i, j);
                    list.add(pair);
                }
            }
        }
        if (list.isEmpty()) {
            // 不存在为 1 的点
            return 0;
        }

        int min = Math.min(row, col);
        for (int i = 2; i <= min; i++) {
            List<Pair<Integer, Integer>> curr = find(matrix, i, list);
            if (curr.isEmpty()) {
                return (i - 1) * (i - 1);
            } else {
                list = curr;
            }
        }

        return min * min;
    }

    /**
     * 查找以某点为左上角，可以组成 n * n 的正方形矩阵的点，
     * 已知以该点为左上角，可以组成 (n - 1) * (n - 1) 的正方形矩阵
     *
     * @param matrix
     * @param n
     * @param list 要查找的点，key 为行索引，value 为列索引
     * @return
     */
    private List<Pair<Integer, Integer>> find(char[][] matrix, int n, List<Pair<Integer, Integer>> list) {
        List<Pair<Integer, Integer>> res = new ArrayList<>();
        for (Pair curr : list) {
            int row = (int) curr.getKey();
            int col = (int) curr.getValue();
            if (row + n - 1 >= this.row || col + n - 1 >= this.col) {
                continue;
            }
            boolean isContinue = false;
            for (int i = row; i < row + n; i++) {
                if (matrix[i][col+n-1] == '0') {
                    isContinue = true;
                    break;
                }
            }
            if (isContinue) {
                continue;
            }
            for (int i = col; i < col + n; i++) {
                if (matrix[row+n-1][i] == '0') {
                    isContinue = true;
                    break;
                }
            }
            if (isContinue) {
                continue;
            }

            res.add(new Pair<>(row, col));
        }

        return res;
    }

    /**
     * 上面的是我一开始自己写的，下面的是优化的动态规划（官方题解）
     */
    public int maximalSquare_2(char[][] matrix) {
        row = matrix.length;    // 行数
        col = row == 0 ? 0 : matrix[0].length;  // 列数
        if (row == 0 || col == 0) {
            return 0;
        }

        int[][] dp = new int[row+1][col+1];
        // dp[i][j] 表示以 matrix[i][j] 为右下角的最大正方形矩阵长度

        int res = Integer.MIN_VALUE;
        for (int i = 1; i < row+1; i++) {
            for (int j = 1; j < col+1; j++) {
                if (matrix[i-1][j-1] == '1') {
                    // 关键是推导出这条公式
                    dp[i][j] = Math.min(dp[i-1][j-1],
                            Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }

        return res * res;
    }

    /**
     * 针对上方动态规划的空间优化
     */
    public int maximalSquare_2_space_better(char[][] matrix) {
        row = matrix.length;    // 行数
        col = row == 0 ? 0 : matrix[0].length;  // 列数
        if (row == 0 || col == 0) {
            return 0;
        }

        int[] dp = new int[col+1];
        // dp[i] 表示以上一行第 i 列的元素为右下角的最大正方形矩阵长度
        // 而 dp[i-1] 已经变成以本行第 i - 1 列的元素为右下角的最大正方形矩阵长度
        int pre = 0;
        // 保存以当前元素左上方为右下角的最大正方形矩阵长度

        int res = Integer.MIN_VALUE;
        for (int i = 1; i < row+1; i++) {
            for (int j = 1; j < col+1; j++) {
                if (matrix[i-1][j-1] == '1') {
                    int temp = dp[j];   // 在更新 d[j] 后，把原 d[j] 赋给 pre
                    dp[j] = Math.min(dp[j-1], Math.min(dp[j], pre)) + 1;
                    res = Math.max(res, dp[j]);
                    pre = temp;
                } else {
                    // 如果当前位置为 0，更新 d[j] 的值，pre 可以不用管
                    dp[j] = 0;
                }
            }
        }

        return res * res;
    }
}
