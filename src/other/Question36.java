package other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Question36 {
    public static void main(String[] args) {

    }

    /**
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     * 1. 数字 1-9 在每一行只能出现一次。
     * 2. 数字 1-9 在每一列只能出现一次。
     * 3. 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     *
     * 说明：
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     * 一个有效的数独（部分已被填充）不一定是可解的。
     * 只需要根据以上规则，验证已经填入的数字是否有效即可。
     * 给定数独序列只包含数字 1-9 和字符 '.' 。
     * 给定数独永远是 9x9 形式的。
     *
     * @param board
     * @return
     */
    private static boolean isValidSudoku(char [][] board) {
        HashSet<Character> row;                     //存储每行
        List<HashSet<Character>> columnList = new ArrayList<>();     //存储每列
        List<HashSet<Character>> matrixList  = new ArrayList<>();     //存储3x3宫
        for (int i = 0; i < board.length; i++) {
            columnList.add(new HashSet<>());
            matrixList.add(new HashSet<>());
        }

        for (int i = 0; i < board.length; i++) {
            row = new HashSet<>();
            for (int j = 0; j < board[i].length; j++) {
                char curr = board[i][j];
                if (curr != '.') {
                    if (!row.add(curr)) {
                        return false;
                    }
                    if (!columnList.get(j).add(curr)) {
                        return false;
                    }
                    if (i / 3 == 0) {
                        int index = j / 3;
                        if (!matrixList.get(index).add(curr)) {
                            return false;
                        }
                    } else if (i / 3 == 1) {
                        int index = j / 3 + 3;
                        if (!matrixList.get(index).add(curr)) {
                            return false;
                        }
                    } else {
                        int index = j / 3 + 6;
                        if (!matrixList.get(index).add(curr)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
