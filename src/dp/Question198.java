package dp;

public class Question198 {
    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        System.out.println(new Question198().rob(nums));
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
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
        }

//        int[] dp = new int[nums.length];
//        // dp[a] 表示到 a[i] 为止（包含 a[i]），窃取的最高金额
//
//        // 初始条件
//        dp[0] = nums[0];
//        dp[1] = Math.max(nums[0], nums[1]);
//        dp[2] = Math.max((nums[0] + nums[2]), nums[1]);
//        // 递推
//        for (int i = 3; i < nums.length; i++) {
//            dp[i] = nums[i] + Math.max(dp[i-2], dp[i-3]);
//        }
//
//        return Math.max(dp[nums.length - 1], dp[nums.length - 2]);

        // 简化版动态规划
        // 只需保存到前两个为止的最高金额
        int pre1 = nums[0];
        int pre2 = Math.max(nums[0], nums[1]);
        int res = 0;
        for (int i = 2; i < nums.length; i++) {
            res = Math.max(pre1 + nums[i], pre2);
            pre1 = pre2;
            pre2 = res;
        }

        return res;
    }
}
