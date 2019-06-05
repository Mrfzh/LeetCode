package division;

public class Question74 {
    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7}, {10,11,16,20}, {23,30,34,50}};
        System.out.println(searchMatrix(matrix, 3));
        System.out.println(searchMatrix(matrix, 13));
        System.out.println(searchMatrix(matrix, 20));
        System.out.println(searchMatrix(matrix, 24));
    }

    /**
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     * 1. 每行中的整数从左到右按升序排列。
     * 2. 每行的第一个整数大于前一行的最后一个整数。
     *
     * 二分查找
     *
     * @param matrix
     * @param target
     * @return
     */
    private static boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;      //行数
        int m = (n == 0)? 0 : matrix[0].length;     //列数
        //边界判断
        if (n < 1 || m < 1) {
            return false;
        }

        //先找出目标可能所在行
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = (left + right) / 2 + 1;
            if (matrix[mid][0] > target) {
                right = mid - 1;
            } else if (matrix[mid][0] < target) {
                left = mid;
            } else {
                return true;
            }
        }
        //循环过后left == right，left（right）行即为目标可能所在行
        //再在该行进行二分查找
        int temp = left;
        left = 0;
        right = m - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (matrix[temp][mid] > target) {
                right = mid - 1;
            } else if (matrix[temp][mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
