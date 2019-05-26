package other;

public class Question9 {
    public static void main(String[] args) {
        System.out.println(isPalindrome_2(10));
    }

    /**
     * 判断一个整数是否是回文数。
     *
     * @param x 要判断的整数
     * @return 判断结果
     */
    private static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int x2;
        StringBuilder builder = new StringBuilder(String.valueOf(x));
        try {
            x2 = Integer.valueOf(builder.reverse().toString());   //反转后的整数
        } catch (NumberFormatException e) {
            return false;   //溢出了就不是回文了
        }

        return x == x2;
    }

    /**
     * 一个更好的方法
     *
     * @param x
     * @return
     */
    private static boolean isPalindrome_2(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {    //以0结尾的肯定不是回文数（0除外）
            return false;
        }

        //从后面开始，反转一半的数
        int x2 = 0;     //存储从后面开始反转，反转后的数

        while (x > x2) {
            x2 = x2 * 10 + x % 10;
            x /= 10;
        }

        return x == x2 || x == x2/10;
    }
}
