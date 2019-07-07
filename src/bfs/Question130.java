package bfs;

public class Question130 {
    public static void main(String[] args) {

    }

    private int m, n;

    /**
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * bfs
     *
     * @param board
     */
    public void solve(char[][] board) {
        m = board.length;   //m行
        n = (m == 0)? 0 : board[0].length;  //n列

        if (m <= 2 || n <= 2) {
            return;
        }

        //先将没有被包围的O（从边界开始连通）换成#
        for (int i = 0; i < n; i++) {
            find(board, 0, i);
        }
        for (int i = 0; i < n; i++) {
            find(board, m-1, i);
        }
        for (int i = 1; i < m-1; i++) {
            find(board, i, 0);
        }
        for (int i = 1; i < m-1; i++) {
            find(board, i, n-1);
        }

        //遍历全部，将O换成X，将#换成O
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * 从board[i,j]开始，查找连通的O，将其换成#
     */
    private void find(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n ||
                board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }

        board[i][j] = '#';
        find(board, i + 1, j);
        find(board, i - 1, j);
        find(board, i, j + 1);
        find(board, i, j - 1);
    }
}
