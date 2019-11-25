package easy;

public class Question724 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Question724().pivotIndex(nums));
    }

    /**
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
     * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     *
     * 如果数组不存在中心索引，那么我们应该返回 -1。
     * 如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
     *
     * 说明:
     * nums 的长度范围为 [0, 10000]。
     * 任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        // 边界情况
        if (nums.length == 0) {
            return -1;
        }
        // 先计算出所有元素的总和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (currSum * 2 + nums[i] == sum) {
                return i;
            }
            currSum += nums[i];
        }

        return -1;
    }
}
