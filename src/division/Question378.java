package division;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Question378 {
    public static void main(String[] args) {
        int[][] matrix = {{1,  5,  9}, {3, 11, 13}, {10, 12, 15}};
        System.out.println(new Question378().kthSmallest_division(matrix, 5));
    }

    /**
     * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
     * 请注意，它是排序后的第k小元素，而不是第k个元素。
     *
     * 优先队列
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        boolean[][] hasFound = new boolean[n][n];
        hasFound[0][0] = true;

        // Pair 中的 key 代表行索引，value 代表列索引
        PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(
                new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return matrix[o1.getKey()][o1.getValue()] - matrix[o2.getKey()][o2.getValue()];
            }
        });
        queue.add(new Pair<>(0, 0));

        int count = 0;
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> currPair = queue.poll();
            int rowIndex = currPair.getKey();   // 当前行索引
            int colIndex = currPair.getValue(); // 当前列索引
            count++;
            if (count == k) {
                return matrix[rowIndex][colIndex];
            }

            if (rowIndex + 1 < n && !hasFound[rowIndex+1][colIndex]) {
                hasFound[rowIndex+1][colIndex] = true;
                queue.offer(new Pair<>(rowIndex+1, colIndex));
            }
            if (colIndex + 1 < n && !hasFound[rowIndex][colIndex+1]) {
                hasFound[rowIndex][colIndex+1] = true;
                queue.offer(new Pair<>(rowIndex, colIndex+1));
            }
        }

        return 0;
    }

    /**
     * 优化：二分法
     */
    public int kthSmallest_division(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n-1][n-1];
        while (left < right) {
            int mid = left + (right - left)/2;
            // 计算矩阵中小于等于 mid 的元素个数
            int count = cal(matrix, mid);
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * 统计矩阵 matrix 中小于等于 target 的元素个数
     */
    private int cal(int[][] matrix, int target) {
        int count = 0;
        for (int[] row : matrix) {
            int left = 0;
            int right = row.length-1;
            while (left <= right) {
                int mid = left + (right - left)/2;
                if (row[mid] <= target) {
                    left = mid + 1;
                } else if (row[mid] > target) {
                    right = mid - 1;
                }
            }
            count += left;
        }

        return count;
    }
}
