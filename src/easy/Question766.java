package easy;

public class Question766 {
    public static void main(String[] args) {

    }

    /**
     * 如果一个矩阵的每一方向由左上到右下的对角线上具有相同元素，那么这个矩阵是托普利茨矩阵。
     * 给定一个 M x N 的矩阵，当且仅当它是托普利茨矩阵时返回 True。
     *
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        // 遍历第一行和第一列的元素，如果以这些元素开头的对角线上的元素都相同
        // 则说明该矩阵为托普利茨矩阵
        for (int i = 0; i < matrix.length; i++) {   // 第一列元素
            if (!isValid(matrix, i, 0)) {
                return false;
            }
        }
        for (int i = 1; i < matrix[0].length; i++) {   // 第一行元素
            if (!isValid(matrix, 0, i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断以 matrix[m][n] 开头的对角线的元素是否相同
     */
    private boolean isValid(int[][] matrix, int m, int n) {
        int first = matrix[m][n];
        while (m < matrix.length && n < matrix[0].length) {
            if (matrix[m][n] != first) {
                return false;
            }
            m++;
            n++;
        }

        return true;
    }
}
