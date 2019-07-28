package recursion;

public class Question50 {
    public static void main(String[] args) {
        System.out.println(new Question50().myPow(2.00000, 10));
        System.out.println(new Question50().myPow(2.10000, 3));
        System.out.println(new Question50().myPow(2.00000, -2));
    }

    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     *
     * 递归
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (x == 0 && n > 0) {
            return 0;
        }

        if (n > 0) {
            return posPow(x, n);
        }
        return 1.0f / posPow(x, n);
    }

    /**
     * 计算 x 的 n 次方，n > 0
     */
    private double posPow(double x, int n) {
        if (n < 0) {
            n = -n;
        }
        if (n == 1) {
            return x;
        }
        double half = posPow(x, n >> 1);
        double res = half * half;
        // 如果 n 为奇数，需要再乘一次 x
        if (n % 2 == 1) {
            res *= x;
        }

        return res;
    }
}
