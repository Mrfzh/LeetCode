package dp;

public class Question740 {
    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 3, 3, 4};
        System.out.println(new Question740().deleteAndEarn(nums));
    }

    /**
     * 给定一个整数数组 nums ，你可以对它进行一些操作。
     *
     * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。
     * 之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
     *
     * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
     *
     * 注意:
     * nums的长度最大为20000。
     * 每个整数nums[i]的大小都在[1, 10000]范围内。
     *
     * dp
     *
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 找到最大和最小的数
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        // record[a] 表示大小为 a 的元素有多少个
        int[] record = new int[max+1];
        for (int num : nums) {
            record[num]++;
        }
        // dp[a] 表示到 a 为止获得的最大点数
        int[] dp = new int[max+1];
        dp[min] = record[min] * min;
        for (int i = min+1; i <= max; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + record[i] * i);
        }

        return dp[max];
    }
}
