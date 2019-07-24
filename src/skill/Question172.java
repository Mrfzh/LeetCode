package skill;

public class Question172 {
    public static void main(String[] args) {
        System.out.println(new Question172().trailingZeroes(3));
        System.out.println(new Question172().trailingZeroes(5));
    }

    /**
     * 给定一个整数 n，返回 n! 结果尾数中零的数量。
     *
     * 找规律，找 5 的个数，注意，像 25 = 5 * 5，有两个 5，
     * 所有要递归查找，直到商为 0
     */
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n/5 + trailingZeroes(n/5);
    }
}
