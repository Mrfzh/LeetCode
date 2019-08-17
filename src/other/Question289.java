package other;

public class Question289 {
    public static void main(String[] args) {

    }

    private int rowNum;
    private int colNum;

    /**
     * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
     * 每个细胞具有一个初始状态 ：live（1）即为活细胞， 或 dead（0）即为死细胞。
     * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     * 1. 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     * 2. 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 3. 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     * 4. 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     *
     * 根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。
     * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
     *
     * @param board
     */
    public void gameOfLife(int[][] board) {
        rowNum = board.length;
        colNum = (rowNum == 0)? 0 : board[0].length;
        if (rowNum == 0 || colNum == 0) {
            return;
        }

        int[][] temp = new int[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                int currLiveCount = liveCount(board, i, j);
                if (currLiveCount < 2 || currLiveCount > 3) {
                    temp[i][j] = 0;
                } else if (currLiveCount == 3) {
                    temp[i][j] = 1;
                } else {
                    temp[i][j] = board[i][j];
                }
            }
        }

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                board[i][j] = temp[i][j];
            }
        }
    }

    /**
     * 求细胞 board[row][col] 周围的活细胞数
     */
    private int liveCount(int[][] board, int row, int col) {
        int count = 0;
        if (row - 1 >= 0 && col - 1 >= 0) {
            count += board[row-1][col-1];
        }
        if (row - 1 >= 0) {
            count += board[row-1][col];
        }
        if (row - 1 >= 0 && col + 1 < colNum) {
            count += board[row-1][col+1];
        }
        if (col - 1 >= 0) {
            count += board[row][col-1];
        }
        if (col + 1 < colNum) {
            count += board[row][col+1];
        }
        if (row + 1 < rowNum && col - 1 >= 0) {
            count += board[row+1][col-1];
        }
        if (row + 1 < rowNum) {
            count += board[row+1][col];
        }
        if (row + 1 < rowNum && col + 1 < colNum) {
            count += board[row+1][col+1];
        }

        return count;
    }
}
