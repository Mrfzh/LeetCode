package bfs;

import java.util.Arrays;
import java.util.LinkedList;

public class Question542 {
    public static void main(String[] args) {
        int[][] matrix = {{0,0,0}, {0,1,0}, {1,1,1}};
        int[][] res = new Question542().updateMatrix(matrix);
        for (int[] a : res) {
            System.out.println(Arrays.toString(a));
        }
    }

    /**
     * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
     *
     * 两个相邻元素间的距离为 1 。
     *
     * 队列 + bfs
     *
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        int row = matrix.length;    // 行
        int col = matrix.length == 0? 0 : matrix[0].length; // 列
        int[][] res = new int[row][col];
        if (row == 0 || col == 0) {
            return res;
        }

        // 如果是 0 则最近距离为 0，在 0 的四周的数最近距离为 1，以此类推，由 0 到 i 向四周扩散
        // 利用队列存储刚赋值的位置，然后寻找该位置的四周，赋下一个值，连续两个元素为一组坐标(x, y)
        LinkedList<Integer> queue = new LinkedList<>();
        // 先寻找 0
        // 不是 0 的元素先初始化为 -1，这样之后在遍历时不是 -1 就表示已经取值
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(i);
                    queue.add(j);
                } else {
                    res[i][j] = -1;
                }
            }
        }
        int dis = 1;    // 下一距离
        while (!queue.isEmpty()) {
            int count = queue.size();   // 这一层的个数
            while (count > 0) {
                int x = queue.remove();
                int y = queue.remove();
                // 在 (x, y) 四周，还未赋值的数，就赋值为 dis，并将其坐标加入队列
                if (x + 1 < row && res[x+1][y] == -1) {
                    res[x+1][y] = dis;
                    queue.add(x+1);
                    queue.add(y);
                }
                if (x - 1 >= 0 && res[x-1][y] == -1) {
                    res[x-1][y] = dis;
                    queue.add(x-1);
                    queue.add(y);
                }
                if (y + 1 < col && res[x][y+1] == -1) {
                    res[x][y+1] = dis;
                    queue.add(x);
                    queue.add(y+1);
                }
                if (y - 1 >= 0 && res[x][y-1] == -1) {
                    res[x][y-1] = dis;
                    queue.add(x);
                    queue.add(y-1);
                }
                count -= 2;
            }
            dis++;  // 下一次循环时距离加 1
        }

        return res;
    }
}
