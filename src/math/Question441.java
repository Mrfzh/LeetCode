package math;

public class Question441 {
    public static void main(String[] args) {
        System.out.println(new Question441().arrangeCoins(5));
        System.out.println(new Question441().arrangeCoins(8));
    }

    /**
     * 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
     *
     * 给定一个数字 n，找出可形成完整阶梯行的总行数。
     * n 是一个非负整数，并且在32位有符号整型的范围内。
     *
     * 数学
     *
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        // 注意转化为 double，因为用 int 可能会溢出
        int temp = (int) Math.sqrt((double) n * 2);
        // 结果只会比 temp 小
        for (int i = temp; i > 0; i--) {
            double sum = (double)i * (double)(i+1) / 2;
            if (sum <= n) {
                return i;
            }
        }

        return 0;
    }
}
