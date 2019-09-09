package other;

public class Question400 {
    public static void main(String[] args) {
        System.out.println(new Question400().findNthDigit(3));
        System.out.println(new Question400().findNthDigit(10));
        System.out.println(new Question400().findNthDigit(2147483647));
    }

    /**
     * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        // 先确定是在多少位数的范围内
        // 各范围的数字个数为：1 x 9, 2 x 90, 3 x 900, ...
        int left = 1;
        long right = 9;
        long next = left * right;   // right 和 next 用 long 避免溢出
        while (n > next) {
            n -= next;
            left++;
            right *= 10;
            next = left * right;
        }
        // 此时 n <= next，确定 n 为该范围的第 a 个数中的第 b 位
        int a = (n+left-1) / left;
        int b = n - (a-1)*left;
        // 确定起始数字 c
        int c = (int) Math.pow(10, left-1);
        // 求出第 a 个数，设为 d
        int d = c + a - 1;
        String sd = String.valueOf(d);
        // 返回第 a 个数的第 b 位
        return sd.charAt(b-1) - '0';
    }
}
