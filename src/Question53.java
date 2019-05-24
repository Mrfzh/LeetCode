public class Question53 {
    public static void main(String[] args) {
        int [] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 算是简化版的动态规划
     *
     * @param nums
     * @return
     */
    private static int maxSubArray(int[] nums) {
        int max = nums[0];
        int currMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                if (currMax > 0) {
                    currMax = currMax + nums[i];
                } else {
                    currMax = nums[i];
                }
            } else {
                if (currMax > 0) {
                    currMax = currMax + nums[i];
                } else {
                    if (nums[i] > currMax) {
                        currMax = nums[i];
                    }
                }
            }
            //每一次都比较
            if (currMax > max) {
                max = currMax;
            }
        }

        return max;
    }
}
