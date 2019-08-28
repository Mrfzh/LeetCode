package division;

public class Question367 {
    public static void main(String[] args) {
        System.out.println(new Question367().isPerfectSquare(16));
        System.out.println(new Question367().isPerfectSquare(14));
        System.out.println(new Question367().isPerfectSquare(481655));
    }

    /**
     * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
     *
     * 说明：不要使用任何内置的库函数
     *
     * 二分
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }

        int left = 2;
        int right = num/2;
        while (left <= right) {
            int mid = left + (right - left)/2;
            int a = num / mid;
            if (a == mid) {
                if (mid * mid == num) {
                    return true;
                } else if (mid * mid < num) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

            } else if (a < mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }
}
