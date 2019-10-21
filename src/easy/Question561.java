package easy;

import java.util.Arrays;

public class Question561 {
    public static void main(String[] args) {
        int[] nums = {1,4,3,2};
        System.out.println(new Question561().arrayPairSum(nums));
    }

    /**
     * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，
     * 使得从1 到 n 的 min(ai, bi) 总和最大。
     *
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        // 先对数组排序
        Arrays.sort(nums);
        // 返回索引为偶数的元素之和
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }

        return res;
    }
}
