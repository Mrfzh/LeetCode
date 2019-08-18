package skill;

public class Question292 {
    public static void main(String[] args) {
        System.out.println(new Question292().canWinNim(50));
    }

    /**
     * 你和你的朋友，两个人一起玩 Nim 游戏：桌子上有一堆石头，每次你们轮流拿掉
     *  1 - 3 块石头。 拿掉最后一块石头的人就是获胜者。你作为先手。
     *
     * 你们是聪明人，每一步都是最优解。
     * 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        if (n < 4) {
            return true;
        }

        boolean first = true;
        boolean second = true;
        boolean third = true;

        for (int i = 4; i <= n; i++) {
            boolean temp = third;
            third = !first || !second || !third;
            first = second;
            second = temp;
        }

        return third;
    }

    /**
     * 动态规划超时，通过找规律优化
     */
    public boolean canWinNim_better(int n) {
        return n % 4 != 0;
    }
}
