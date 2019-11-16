package easy;

public class Question693 {
    public static void main(String[] args) {
        System.out.println(new Question693().hasAlternatingBits(5));
        System.out.println(new Question693().hasAlternatingBits(7));
        System.out.println(new Question693().hasAlternatingBits(11));
        System.out.println(new Question693().hasAlternatingBits(10));
    }

    /**
     * 给定一个正整数，检查他是否为交替位二进制数：换句话说，就是他的二进制数相邻的两个位数永不相等。
     *
     * @param n
     * @return
     */
    public boolean hasAlternatingBits(int n) {
        int last = n & 1;
        n = n >> 1;
        while (n > 0) {
            int curr = n & 1;
            if (curr == last) {
                return false;
            }
            last = curr;
            n = n >> 1;
        }

        return true;
    }
}
