package other;

import java.util.ArrayList;
import java.util.List;

public class Question54 {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(spiralOrder(matrix));
    }

    /**
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     *
     * @param matrix
     * @return
     */
    private static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if(matrix.length == 0) {
            return result;
        }

        int row = matrix.length;     //行数
        int column = matrix[0].length;     //列数
        int min = row < column? row : column;
        int cycles = min / 2;    //圈数

        //由外到内，按圈数遍历
        for (int i = 0; i < cycles; i++) {
            int left = i;
            int right = column - i - 1;
            int top = i;
            int bottom = row - i - 1;

            for (int j = left; j <= right; j++) {   //上
                result.add(matrix[top][j]);
            }
            for (int j = top+1; j < bottom; j++) {   //右
                result.add(matrix[j][right]);
            }
            for (int j = right; j >= left; j--) {    //下
                result.add(matrix[bottom][j]);
            }
            for (int j = bottom-1; j > top; j--) {   //左
                result.add(matrix[j][left]);
            }
        }

        //处理特殊情况
        if (min % 2 == 1) {
            int left = cycles;
            int right = column - cycles - 1;
            int top = cycles;
            int bottom = row - cycles - 1;

            if (left == right) {
                for (int i = top; i <= bottom; i++) {
                    result.add(matrix[i][left]);
                }
            } else {
                for (int i = left; i <= right; i++) {
                    result.add(matrix[top][i]);
                }
            }
        }

        return result;
    }

}
