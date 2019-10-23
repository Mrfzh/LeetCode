package easy;

public class Question566 {
    public static void main(String[] args) {

    }

    /**
     * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
     *
     * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     *
     * 注意：
     * 给定矩阵的宽和高范围在 [1, 100]。
     * 给定的 r 和 c 都是正数。
     *
     * @param nums
     * @param r
     * @param c
     * @return
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        // 原矩阵的行数和列数
        int row = nums.length;
        int col = nums[0].length;
        // 判断是否可行，若不可行则直接返回原数组
        if (row * col != r * c) {
            return nums;    //
        }
        // 当前遍历到的原数组的坐标(x, y)
        int x = 0;
        int y = 0;
        // 按照行顺序填充数组
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (y == col) {
                    x++;
                    y = 0;
                }
                res[i][j] = nums[x][y];
                y++;
            }
        }

        return res;
    }
}
