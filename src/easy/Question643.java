package easy;

public class Question643 {
    public static void main(String[] args) {
        int[] nums = {1,12,-5,-6,50,3};
        System.out.println(new Question643().findMaxAverage(nums, 4));
    }

    /**
     * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
     *
     * 注意:
     * 1 <= k <= n <= 30,000。
     * 所给数据范围 [-10,000，10,000]。
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        // sum 初始化为前 k 个数的和
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        // 先找出长度为 k 的连续子数组的和的最大值
        int max = sum;
        // 查找之后的和
        for (int i = 1; i + k <= nums.length; i++) {
            sum = sum - nums[i-1] + nums[i+k-1];
            max = Math.max(sum, max);
        }

        return (double)max / k;
    }
}
