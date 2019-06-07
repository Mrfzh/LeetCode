package back;

public class Question79 {
    public static void main(String[] args) {
        Question79 question79 = new Question79();
        char[][] board = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        System.out.println(question79.exist(board, "ABCCED"));
        System.out.println(question79.exist(board, "SEE"));
        System.out.println(question79.exist(board, "ABCB"));
    }

    /**
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
     * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     * 回溯法
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int n = board.length;       //行数
        int m = (n == 0)? 0 : board[0].length;  //列数
        //边界判断
        if (word.equals("")) {
            return true;
        }
        if (n < 1 || m < 1) {
            return false;
        }

        char [] str = word.toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //找到第一个字母
                if (board[i][j] == str[0]) {
                    int [][] isFound = new int[n][m];
                    isFound[i][j] = 1;
                    if (find(isFound, board, i, j, str, 1)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 以board（x，y）为起点，寻找的下一字母为str[start]
     *  isFound[a][b]为1时，说明(a,b)已找过，不能再重复
     */
    private boolean find(int [][] isFound, char[][] board, int x, int y, char [] str, int start) {
        if (start == str.length) {  //全部字母找到
            return true;
        }

        //寻找相邻位置
        int nx, ny;
        nx = x-1; ny = y;
        if ( nx >= 0 && isFound[nx][ny] == 0 && board[nx][ny] == str[start]) {
            int newFound [][] = new int[isFound.length][isFound[0].length];
            copyArray(isFound, newFound);
            newFound[nx][ny] = 1;    //该点已找过
            if (find(newFound, board, nx, ny, str, start + 1)) {
                return true;
            }
        }
        nx = x; ny = y-1;
        if (ny >= 0 && isFound[nx][ny] == 0 && board[nx][ny] == str[start]) {
            int newFound [][] = new int[isFound.length][isFound[0].length];
            copyArray(isFound, newFound);
            newFound[nx][ny] = 1;
            if (find(newFound, board, nx, ny, str, start + 1)) {
                return true;
            }
        }
        nx = x; ny = y+1;
        if (ny < board[0].length && isFound[nx][ny] == 0 && board[nx][ny] == str[start]) {
            int newFound [][] = new int[isFound.length][isFound[0].length];
            copyArray(isFound, newFound);
            newFound[nx][ny] = 1;
            if (find(newFound, board, nx, ny, str, start + 1)) {
                return true;
            }
        }
        nx = x+1; ny = y;
        if (nx < board.length && isFound[nx][ny] == 0 && board[nx][ny] == str[start]) {
            isFound[nx][ny] = 1;
            if (find(isFound, board, nx, ny, str, start + 1)) {
                return true;
            }
        }

        return false;
    }

    private void copyArray(int[][] oldArray, int [][] newArray) {
        for (int i = 0; i < oldArray.length; i++) {
            for (int j = 0; j < oldArray[0].length; j++) {
                newArray[i][j] = oldArray[i][j];
            }
        }
    }
}
