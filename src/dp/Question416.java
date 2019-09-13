package dp;

import java.util.Arrays;

public class Question416 {
    public static void main(String[] args) {
        int[] nums = {100};
        System.out.println(new Question416().canPartition(nums));
    }

    /**
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * 注意:
     * 每个数组中的元素不会超过 100
     * 数组的大小不会超过 200
     *
     * dp
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        // 计算数组和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 如果数组和为奇数，则不存在结果
        if ((sum & 1) == 1) {
            return false;
        }

        Arrays.sort(nums);
        int target = sum >> 1;
        // 接下来求数组是否存在和为 target 的子集
        // dp[a] 表示数组中是否存在和为 a 的子集
        boolean[] dp = new boolean[target+1];
        // 先加入第一个数
        if (nums[0] == target) {
            return true;
        } else if (nums[0] < target) {
            dp[nums[0]] = true;
        } else {
            return false;
        }
        // 依次加入后面的数
        for (int i = 1; i < nums.length; i++) {
            if (target - nums[i] >= 0) {
                dp[target] = dp[target - nums[i]];
            }
            // 如果找到和为 target 的子集，直接返回 true
            if (dp[target]) {
                return true;
            }
            for (int j = target-1; j >= nums[i]; j--) {
                // 如果之前没有和为 j 的子集
                if (!dp[j]) {
                    // 若之前有和为 j - nums[i] 的子集的话，该子集加入 nums[i]，即拥有和为 j 的子集
                    dp[j] = dp[j - nums[i]];
                }
            }
        }

        return false;
    }

}
