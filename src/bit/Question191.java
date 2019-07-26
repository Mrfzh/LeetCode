package bit;

public class Question191 {
    public static void main(String[] args) {
        System.out.println(new Question191().hammingWeight(-3));
    }

    /**
     * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中
     * 数字位数为 ‘1’ 的个数（也被称为汉明重量）。
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int res = 0;
        int flag = 1;
        for (int i = 0; i < 32; i++) {
            res += (n & flag) == 0 ? 0 : 1;
            flag = flag << 1;
        }

        return res;
    }
}
