package easy;

public class Question657 {
    public static void main(String[] args) {
        System.out.println(new Question657().judgeCircle("UD"));
        System.out.println(new Question657().judgeCircle("LL"));
    }

    /**
     * 在二维平面上，有一个机器人从原点 (0, 0) 开始。
     * 给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
     *
     * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。
     * 机器人的有效动作有 R（右），L（左），U（上）和 D（下）。
     * 如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
     *
     * @param moves
     * @return
     */
    public boolean judgeCircle(String moves) {
        // 也就是判断字符串中 'L' 和 'R' 、'U' 和 'D'的数量是否相等
        int LNum = 0;
        int RNum = 0;
        int UNum = 0;
        int DNum = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'L') {
                LNum++;
            } else if (c == 'R') {
                RNum++;
            } else if (c == 'U') {
                UNum++;
            } else if (c == 'D') {
                DNum++;
            }
        }

        return LNum == RNum && UNum == DNum;
    }
}
