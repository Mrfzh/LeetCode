package bit;

import java.util.Arrays;

public class Question260 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,3,2,5};
        System.out.println(Arrays.toString(new Question260().singleNumber(nums)));
    }

    /**
     * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，
     * 其余所有元素均出现两次。 找出只出现一次的那两个元素。
     *
     * 说明：
     * 1. 结果输出的顺序并不重要
     * 2. 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
     *
     * 位运算
     *
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        // 先求出所有数的异或结果
        // （相同的数异或后为 0，两个不同的数异或后至少有一位不为 0）
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        // 保留异或结果的最低一位 1（利用该位区分两数），其他都为 0
        int mask = xor & (-xor);

        // 找出并存储只出现一次的元素
        int[] res = new int[2];
        for (int num : nums) {
            if ((num & mask) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }

        return res;
    }
}
