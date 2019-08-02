package dp;

public class Question213 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(new Question213().rob(nums));
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
     * 这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
     * 同时，相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，
     * 计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *
     * 动态规划
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        } else if (nums.length == 3) {
            return Math.max(Math.max(nums[0], nums[1]), nums[2]);
        }

        int maxExcludeFirst = 0;    // 不含 nums[0] 的最大值
        int maxContainFirst = 0;    // 包含 nums[0] 的最大值

        // 先计算不含 nums[0] 的最大值
        int lastMax = 0;
        int currMax = nums[1];
        for (int i = 2; i < nums.length; i++) {
            int temp = Math.max(lastMax + nums[i], currMax);
            lastMax = currMax;
            currMax = temp;
        }
        maxExcludeFirst = currMax;

        // 再计算包含 nums[0] 的最大值
        lastMax = nums[0];
        currMax = nums[0];
        for (int i = 2; i < nums.length - 1; i++) {
            int temp = Math.max(lastMax + nums[i], currMax);
            lastMax = currMax;
            currMax = temp;
        }
        maxContainFirst = currMax;

        return Math.max(maxExcludeFirst, maxContainFirst);
    }
}
