package dp;

import java.util.Arrays;

public class Question338 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Question338().countBits(5)));
    }

    /**
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，
     * 计算其二进制数中的 1 的数目并将它们作为数组返回。
     *
     * dp + 位运算
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] res = new int[num+1]; // res[a] 表示 a 中 1 的数目
        res[0] = 0;
        int flag = 1;
        for (int i = 1; i <= num; i++) {
            if ((i & flag) == 0) {
                flag = flag << 1;
            }
            // i - flag 表示将 i 最左边的 1 移除
            res[i] = res[i - flag] + 1;
        }

        return res;
    }
}
