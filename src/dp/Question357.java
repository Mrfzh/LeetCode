package dp;

public class Question357 {
    public static void main(String[] args) {
        System.out.println(new Question357().countNumbersWithUniqueDigits(2));
    }

    /**
     * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10^n 。
     *
     * dp
     *
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        if (n > 10) {
            n = 10;
        }

        int last = 10;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            int sum = 9;
            int temp = 9;
            for (int j = 0; j < i-1; j++) {
                sum *= temp;
                temp--;
            }
            // 计算当前结果
            res = last + sum;
            last = res;
        }

        return res;
    }
}
