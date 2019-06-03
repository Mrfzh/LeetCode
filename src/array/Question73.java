package array;

import java.util.Arrays;

public class Question73 {
    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
        setZeroes(matrix);
        for (int i = 0; i < matrix.length; i++) {
            int [] curr = matrix[i];
            System.out.println(Arrays.toString(curr));
        }
    }

    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     *
     * @param matrix
     */
    private static void setZeroes(int[][] matrix) {
        int n = matrix.length;      //行数
        int m = (n == 0)? 0 : matrix[0].length;     //列数

        if (n < 1 || m < 1) {
            return;
        }

        int [] rows = new int[n];       //rows[i]为1说明第i行置0，columns同理
        int [] columns = new int[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = 1;
                    columns[j] = 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (rows[i] == 1) {
                //第i行置0
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (columns[i] == 1) {
                //第i列置0
                for (int j = 0; j < n; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }
}
