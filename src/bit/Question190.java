package bit;

public class Question190 {
    public static void main(String[] args) {
        System.out.println(new Question190().reverseBits(43261596));
        System.out.println(new Question190().reverseBits(-3));
    }

    /**
     * 颠倒给定的 32 位无符号整数的二进制位。
     *
     * 位运算
     *
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        int flag = 1;
        int res = 0;
        // 从低到高，得到对应位的值
        for (int i = 0; i < 32; i++) {
            // 当前位的值
            int curr = (flag & n) == 0 ? 0 : 1;
            // 将当前位的值放到最后
            res = (res << 1) + curr;
            flag = flag << 1;
        }

        return res;

    }
}
