package dp;

import java.util.Arrays;

public class Question494 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        System.out.println(new Question494().findTargetSumWays_dp(nums, 3));
    }

    private int res = 0;

    /**
     * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
     * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
     *
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     *
     * 注意:
     * 1. 数组非空，且长度不会超过20。
     * 2. 初始的数组的和不会超过1000。
     * 3. 保证返回的最终结果能被32位整数存下。
     *
     * 将问题转化后，用回溯求解
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        // 先求全部数加起来的和
        int sum = 0;
        int zeroNum = 0;    // 0 的个数
        for (int num : nums) {
            sum += num;
            if (num == 0) {
                zeroNum++;
            }
        }
        // 如果总和小于 S，或者总和减去 S 为奇数，不可能有结果
        if (sum < S || (sum - S) % 2 == 1) {
            return 0;
        }
        // target 前面要变成减号的数的和
        int target = (sum - S) / 2;
        // 问题转化为求 nums 数组中和为 target 的子集个数
        // 特殊情况：如果 target 为 0，说明不用减，根据 0 的个数返回不同结果
        if (target == 0) {
            return zeroNum == 0? 1 : (int) Math.pow(2, zeroNum);
        }
        Arrays.sort(nums);
        find(nums, 0, 0, target);

        return res;
    }

    /**
     * 求 nums 数组中和为 target 的子集个数
     * 从 nums[start] 开始，currSum 为当前总和, res 存储结果
     */
    private void find(int[] nums, int start, int currSum, int target) {
        if (start >= nums.length) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (currSum + nums[i] == target) {
                res++;
            } else if (currSum + nums[i] < target) {
                find(nums, i+1, currSum + nums[i], target);
            } else {
                break;
            }
        }
    }

    /**
     * 利用 dp 进行优化
     */
    public int findTargetSumWays_dp(int[] nums, int S) {
        // 先求全部数加起来的和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 如果总和小于 S，或者总和加上 S 为奇数，不可能有结果
        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }
        // target 前面要变成加号的数的和
        int target = (sum + S) / 2;

        // dp[a] 表示 nums 数组中和为 a 的子集个数
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int num : nums) {
            // 把当前 num 加进去（很巧妙）
            for (int i = target; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }

        return dp[target];
    }
}
