package bit;

import java.util.HashSet;

public class Question421 {
    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println(new Question421().findMaximumXOR(nums));
    }

    /**
     * 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。
     * 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。
     *
     * 你能在O(n)的时间解决这个问题吗？
     *
     * 位运算
     *
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        // 先要知道异或运算的一个性质：假如 a ^ b = c，那么 a ^ c = b 且 b ^ c = a
        // 所以假如 nums 数组中存在两个数 a,b 满足 a ^ b = max，那么对于 max 来说，
        // 必定在数组中必定存在一个数 a，使得 max ^ a 的结果 b 存在与数组中

        int max = 0;
        int mask = 0;
        // 从高位开始，计算该位是否可以为 1
        for (int i = 31; i >= 0; i--) {
            // mask 的值为 1..10..0，其中前 32-i 位为 1,后 i 位为 0
            // mask 用于提取元素的前缀
            mask = (1 << i) | mask;
            // 保存元素的前缀
            HashSet<Integer> preSet = new HashSet<>();
            for (int num : nums) {
                preSet.add(mask & num);
            }

            int temp = max | (1 << i);
            for (int num : nums) {
                int pre = mask & num;
                if (preSet.contains(temp ^ pre)) {
                    // 如果存在 a ^ b = max（仅比较前缀），说明这一位可以为 1
                    max = temp;
                    break;
                }
            }
        }

        return max;
    }
}
