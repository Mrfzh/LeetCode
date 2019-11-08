package easy;

public class Question661 {
    public static void main(String[] args) {

    }

    /**
     * 包含整数的二维矩阵 M 表示一个图片的灰度。
     * 你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，
     *
     * 平均灰度的计算是周围的8个单元和它本身的值求平均，
     * 如果周围的单元格不足八个，则尽可能多的利用它们。
     *
     * 注意:
     * 给定矩阵中的整数范围为 [0, 255]。
     * 矩阵的长和宽的范围均为 [1, 150]。
     *
     * 数组辅助记录，避免重复计算
     *
     * @param M
     * @return
     */
    public int[][] imageSmoother(int[][] M) {
        int row = M.length;     // M 的行数
        int col = M[0].length;  // M 的列数
        // record[a][b] 表示 M 的第 a 行，[0, b] 间的和
        int[][] record = new int[row][col];
        for (int i = 0; i < row; i++) {
            int sum = 0;
            for (int j = 0; j < col; j++) {
                sum += M[i][j];
                record[i][j] = sum;
            }
        }

        int[][] res = new int[row][col];
        // 先计算边界
        for (int i = 0; i < col; i++) {
            res[0][i] = calBorder(M, 0, i, row, col);
            res[row-1][i] = calBorder(M, row-1, i, row, col);
        }
        for (int i = 1; i < row - 1; i++) {
            res[i][0] = calBorder(M, i, 0, row, col);
            res[i][col-1] = calBorder(M, i, col-1, row, col);
        }
        // 再计算内部
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                if (j == 1) {
                    int sum = record[i-1][j+1] + record[i][j+1] + record[i+1][j+1];
                    res[i][j] = sum / 9;
                } else {
                    int sum = record[i-1][j+1] - record[i-1][j-2]
                            + record[i][j+1] - record[i][j-2]
                            + record[i+1][j+1] - record[i+1][j-2];
                    res[i][j] = sum / 9;
                }
            }
        }

        return res;
    }

    /**
     * 计算 M[i][j] 的四周平均值
     */
    private int calBorder(int[][] M, int i, int j, int row, int col) {
        int sum = M[i][j];
        int count = 1;
        if (j > 0) {
            sum += M[i][j-1];
            count++;
        }
        if (j < col - 1) {
            sum += M[i][j+1];
            count++;
        }
        if (i > 0) {
            sum += M[i-1][j];
            count++;
            if (j > 0) {
                sum += M[i-1][j-1];
                count++;
            }
            if (j < col - 1) {
                sum += M[i-1][j+1];
                count++;
            }
        }
        if (i < row - 1) {
            sum += M[i+1][j];
            count++;
            if (j > 0) {
                sum += M[i+1][j-1];
                count++;
            }
            if (j < col - 1) {
                sum += M[i+1][j+1];
                count++;
            }
        }

        return sum / count;
    }
}
