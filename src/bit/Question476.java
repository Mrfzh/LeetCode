package bit;

public class Question476 {
    public static void main(String[] args) {
        System.out.println(new Question476().findComplement(5));
        System.out.println(new Question476().findComplement(1));
    }

    /**
     * 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
     *
     * 注意:
     * 1. 给定的整数保证在32位带符号整数的范围内。
     * 2. 你可以假定二进制数不包含前导零位。
     *
     * 位运算
     *
     * @param num
     * @return
     */
    public int findComplement(int num) {
        int flag = 1;   // flag 最终为 0..01..1,1 的个数为 num 中不包含前导零的二进制位数
        int temp = num;
        temp = temp >> 1;
        while (temp != 0) {
            flag = 1 + (flag << 1);
            temp = temp >> 1;
        }

        return flag & (~num);
    }
}
