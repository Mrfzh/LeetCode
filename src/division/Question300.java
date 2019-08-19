package division;

public class Question300 {
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(new Question300().lengthOfLIS(nums));
    }

    /**
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     *
     * dp
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // dp[a] 表示以 nums[a] 开头的最长上升子序列长度
        int[] dp = new int[nums.length];
        dp[nums.length-1] = 1;
        for (int i = nums.length-2; i >= 0; i--) {
            int max = 1;
            for (int j = i+1; nums.length - j >= max; j++) {
                if (nums[j] > nums[i]) {
                    max = Math.max(max, dp[j]+1);
                }
            }
            dp[i] = max;
        }

        // 找出最大值
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }

        return max;
    }

    /**
     * 优化：dp + 二分
     */
    public int lengthOfLIS_better(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // dp[a] 表示长度为 a 的上升子序列的最小尾数
        int[] dp = new int[nums.length];

        int max = 0;    // 当前最长上升子序列的长度
        for (int num : nums) {
            int left = 0;
            int right = max;
            while (left < right) {
                int mid = left + (right - left)/2;
                if (dp[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            // left 为原 dp 数组中比 num 大的下一位元素索引
            // 这样就保证了 dp 数组是一个升序数组
            dp[left] = num;
            if (left == max) {
                max++;
            }
        }

        return max;
    }
}
