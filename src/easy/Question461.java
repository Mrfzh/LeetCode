package easy;

public class Question461 {
    public static void main(String[] args) {
        System.out.println(new Question461().hammingDistance(1,4));
    }

    /**
     * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
     *
     * 给出两个整数 x 和 y，计算它们之间的汉明距离。
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        // 求 x ^ y 后二进制中 1 的个数
        return count(x ^ y);
    }

    /**
     * 求 a 中 1 的个数
     */
    private int count(int a) {
        int count = 0;
        while (a != 0) {
            count++;
            a = a & (a - 1);
        }

        return count;
    }
}
