package skill;

public class Question240 {
    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},
                {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        System.out.println(new Question240().searchMatrix_better(matrix, 5));
        System.out.println(new Question240().searchMatrix_better(matrix, 20));
    }

    private int row;    // 行数
    private int col;    // 列数

    /**
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。
     *
     * 该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        row = matrix.length;
        col = (row == 0)? 0 : matrix[0].length;
        if (row == 0 || col == 0) {
            return false;
        }

        return find(matrix, 0, col-1, target);
    }

    /**
     * 每次和右上角的值比较
     *
     * @param matrix
     * @param rowStart 当前开始行
     * @param colEnd 当前结束列
     * @param target
     * @return
     */
    private boolean find(int[][] matrix, int rowStart, int colEnd, int target) {
        if (rowStart >= row || colEnd < 0) {
            return false;
        }
        int curr = matrix[rowStart][colEnd];
        if (curr == target) {
            return true;
        } else if (curr < target) {
            return find(matrix, rowStart + 1, colEnd, target);
        } else {
            return find(matrix, rowStart, colEnd - 1, target);
        }
    }

    /**
     * 上述解法的优化
     */
    public boolean searchMatrix_better(int[][] matrix, int target) {
        row = matrix.length;
        col = (row == 0)? 0 : matrix[0].length;
        if (row == 0 || col == 0) {
            return false;
        }

        int i = 0;
        int j = col - 1;
        while (i < row && j >= 0) {
            int curr = matrix[i][j];
            if (curr == target) {
                return true;
            } else if (curr > target) {
                j--;
            } else {
                i++;
            }
        }

        return false;
    }
}
