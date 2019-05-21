import java.util.Arrays;

public class Question48 {
    public static void main(String[] args) {
//        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15,14,12,16}};
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    /**
     * 给定一个 n × n 的二维矩阵表示一个图像。将图像顺时针旋转 90 度。
     *
     * 说明：你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     *
     * @param matrix
     */
    private static void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return;
        }

        //从最外面的一圈开始，由外到里排列
        for (int i = 0; i < n/2; i++) {     //总共要旋转的圈数
            int p = 0;  //偏移量
            for (int j = i; j < n-i-1; j++, p++) {  //每一圈的旋转
                int temp = matrix[i][j];
                int end = n - i - 1;
                matrix[i][j] = matrix[end-p][i];
                matrix[end-p][i] = matrix[end][end-p];
                matrix[end][end-p] = matrix[i+p][end];
                matrix[i+p][end] = temp;
            }
        }
    }
}
