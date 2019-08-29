package bit;

public class Question371 {
    public static void main(String[] args) {
        System.out.println(new Question371().getSum(1, 2));
        System.out.println(new Question371().getSum(-2, 3));
        System.out.println(new Question371().getSum(1000, 632));
    }

    /**
     * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
     *
     * 位运算
     *
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        int a1 = a; // 第一个加数
        int a2 = b; // 第二个加数
        int x = a1 & a2;  // 存储 a1 和 a2 的与结果
        // 循环直到没有进位（当 x 为 0 表示没有进位）
        while (x != 0) {
            a1 = (a1 | a2) & (~x);  // 进位以外的相加结果
            a2 = x << 1;    // 进位
            x = a1 & a2;
        }

        return a1 | a2;
    }
}
