package dp;

import java.util.Arrays;

public class Question238 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] res = new Question238().productExceptSelf(nums);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
     * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     *
     * 说明：
     * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     *
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？
     * （ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        // 先求前半部分的乘积
        int sum = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = sum;
            sum *= nums[i];
        }
        // 再乘上后半部分的乘积
        sum = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= sum;
            sum *= nums[i];
        }

        return res;
    }
}
