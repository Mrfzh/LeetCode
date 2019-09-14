package other;

public class Question419 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二维的甲板， 请计算其中有多少艘战舰。 
     * 战舰用 'X' 表示，空位用 '.' 表示。 
     *
     * 你需要遵守以下规则：
     * 1. 给你一个有效的甲板，仅由战舰或者空位组成。
     * 2. 战舰只能水平或者垂直放置。换句话说,战舰只能由 1xN (1 行, N 列)组成，
     * 或者 Nx1 (N 行, 1 列)组成，其中N可以是任意大小。
     * 3. 两艘战舰之间至少有一个水平或垂直的空位分隔 - 即没有相邻的战舰。
     *
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return 0;
        }

        int row = board.length;     // 行数
        int col = board[0].length;  // 列数
        // dp[a] 表示上一行的第 a 个元素是否为 X(战舰)
        boolean[] dp = new boolean[col];
        int res = 0;
        for (int i = 0; i < row; i++) {
            boolean lastIsX = false;    // 前一个元素是否为 X
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'X' && !lastIsX && !dp[j]) {
                    res++;
                    lastIsX = true;
                } else if (board[i][j] == '.') {
                    lastIsX = false;
                }
                dp[j] = board[i][j] == 'X';
            }
        }

        return res;
    }

    /**
     * 优化时间复杂度
     */
    public int countBattleships_better(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return 0;
        }

        int row = board.length;     // 行数
        int col = board[0].length;  // 列数

        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 当前元素为 X，且其左边和上边都不是 X，说明是一艘新的战舰
                if (board[i][j] == 'X' && (j == 0 || board[i][j-1] != 'X') &&
                        (i == 0 || board[i-1][j] != 'X')) {
                    res++;
                }
            }
        }

        return res;
    }
}
