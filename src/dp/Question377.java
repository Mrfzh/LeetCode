package dp;

import java.util.Arrays;

public class Question377 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Question377().combinationSum4(nums, 2));
        System.out.println(new Question377().combinationSum4(nums, 3));
        System.out.println(new Question377().combinationSum4(nums, 4));
        System.out.println(new Question377().combinationSum4(nums, 5));
        System.out.println(new Question377().combinationSum4(nums, 35));
    }

    /**
     * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
     *
     * 请注意，顺序不同的序列被视作不同的组合。
     *
     * dp
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        if (nums[0] > target) {
            return 0;
        }

        // dp[a] 表示和为 a 的组合个数
        int[] dp = new int[target+1];
        dp[0] = 1;
        dp[nums[0]] = 1;
        for (int i = nums[0]+1; i <= target; i++) {
            int sum = 0;
            for (int num : nums) {
                if (num > i) {
                    break;
                }
                sum += dp[i-num];
            }
            dp[i] = sum;
        }

        return dp[target];
    }

}
