package double_pointer;

public class Question713 {
    public static void main(String[] args) {
        int[] nums = {1,1,1};
        System.out.println(new Question713().numSubarrayProductLessThanK(nums, 1));
    }

    /**
     * 给定一个正整数数组 nums。
     * 找出该数组内乘积小于 k 的连续的子数组的个数。
     *
     * 说明:
     * 0 < nums.length <= 50000
     * 0 < nums[i] < 1000
     * 0 <= k < 10^6
     *
     * 双指针
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int res = 0;
        int start = 0;
        int end = 0;
        int sum = 1;    // nums[start, end) 之间的乘积
        while (end < nums.length) {
            end++;
            sum *= nums[end-1];
            // 保证 nums[start, end) 的乘积小于 k
            while (sum >= k && start <= end-1) {
                sum /= nums[start];
                start++;
            }
            // 每一次循环 end 都后移一位，结果加上 end - start
            // 表示加上以 nums[end-1] 结尾的符合条件的连续子数组的个数
            res += end - start;
        }

        return res;
    }
}
