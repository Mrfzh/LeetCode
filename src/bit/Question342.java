package bit;

public class Question342 {
    public static void main(String[] args) {
        System.out.println(new Question342().isPowerOfFour(1));
        System.out.println(new Question342().isPowerOfFour(16));
        System.out.println(new Question342().isPowerOfFour(25));
    }

    /**
     * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
     *
     * 位运算
     *
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
//        if (num < 1) {
//            return false;
//        }
//        return (num & (num - 1)) == 0 && moreCheck(num);

        // 0x55555555 为 01010101.... 表示奇数位为 1，偶数位为 0
        // 而四的幂次方要求 1 所在的位置为奇数位
        return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) != 0;
    }

    /**
     * 如果 num 中 0 的个数为偶数，则返回 true
     */
    private boolean moreCheck(int num){
        int count = 0;
        while (num != 1) {
            count++;
            num = num >> 1;
        }
        return count % 2 == 0;
    }
}
