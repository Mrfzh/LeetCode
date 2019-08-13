package easy;

public class Question263 {
    public static void main(String[] args) {
        System.out.println(new Question263().isUgly(6));
        System.out.println(new Question263().isUgly(8));
        System.out.println(new Question263().isUgly(14));
    }

    /**
     * 编写一个程序判断给定的数是否为丑数。
     *
     * 丑数就是只包含质因数 2, 3, 5 的正整数。(1 是丑数)
     *
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        if (num % 2 == 0) {
            return isUgly(num/2);
        }
        if (num % 3 == 0) {
            return isUgly(num/3);
        }
        if (num % 5 == 0) {
            return isUgly(num/5);
        }
        return false;
    }

    /**
     * 非递归(更快)
     */
    public boolean isUgly_2(int num) {
        if (num <= 0) {
            return false;
        }
        while (num >= 1) {
            if (num == 1) {
                return true;
            }
            if (num % 2 == 0) {
                num /= 2;
                continue;
            }
            if (num % 3 == 0) {
                num /= 3;
                continue;
            }
            if (num % 5 == 0) {
                num /= 5;
                continue;
            }
            return false;
        }
        return false;
    }
}
