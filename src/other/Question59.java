package other;

import java.util.Arrays;

public class Question59 {
    public static void main(String[] args) {
        int[][] result = generateMatrix(1);
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }

    /**
     * 给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     *
     * @param n
     * @return
     */
    private static int[][] generateMatrix(int n) {
        if (n < 1) {
            return new int[0][0];
        }

        int curr = 1;
        int[][] result = new int[n][n];
        //由外到内，按圈数遍历
        for (int i = 0; i < n/2; i++) {
            int left = i;
            int right = n - i - 1;
            int top = i;
            int bottom = n - i - 1;

            for (int j = left; j <= right; j++) {   //上
                result[top][j] = curr++;
            }
            for (int j = top+1; j < bottom; j++) {   //右
                result[j][right] = curr++;
            }
            for (int j = right; j >= left; j--) {    //下
                result[bottom][j] = curr++;
            }
            for (int j = bottom-1; j > top; j--) {   //左
                result[j][left] = curr++;
            }
        }

        //处理特殊情况
        if (n % 2 == 1) {
            result[n/2][n/2] = curr;
        }

        return result;
    }
}
