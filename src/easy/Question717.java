package easy;

public class Question717 {
    public static void main(String[] args) {
        int[] bits = {1,1,1,0};
        System.out.println(new Question717().isOneBitCharacter(bits));
    }

    /**
     * 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
     * 现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
     *
     * 注意:
     * 1 <= len(bits) <= 1000.
     * bits[i] 总是0 或 1.
     *
     * @param bits
     * @return
     */
    public boolean isOneBitCharacter(int[] bits) {
        // 从头到尾遍历，遇到 0 后移一位，遇到 1 后移两位。
        // 看看是否能单独遍历到最后一位 0
        for (int i = 0; i < bits.length; ) {
            if (i == bits.length - 1) {
                return true;
            }
            i += bits[i] == 0? 1 : 2;
        }

        return false;
    }
}
