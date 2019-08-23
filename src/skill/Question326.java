package skill;

import java.util.Arrays;
import java.util.List;

public class Question326 {
    public static void main(String[] args) {
        System.out.println(new Question326().isPowerOfThree(27));
        System.out.println(new Question326().isPowerOfThree(0));
        System.out.println(new Question326().isPowerOfThree(9));
        System.out.println(new Question326().isPowerOfThree(45));
    }

    /**
     * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
     *
     * 进阶：
     * 你能不使用循环或者递归来完成本题吗？
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        List<Integer> list = Arrays.asList(1, 3, 9, 27, 81, 243, 729, 2187, 6561,
                19683, 59049, 177147, 531441, 1594323, 4782969, 14348907, 43046721,
                129140163, 387420489, 1162261467);
        return list.contains(n);
    }

    /**
     * 优化
     */
    public boolean isPowerOfThree_better(int n) {
        // 由于 3 是质数，所以在整数范围内 3 的幂次方都可以
        // 被整数范围内最大的 3 的幂次方整除
        return n > 0 && 1162261467 % n == 0;
    }
}
