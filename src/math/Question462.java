package math;

import java.util.Arrays;

public class Question462 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(new Question462().minMoves2(nums));
    }

    /**
     * 给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，
     * 其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。
     *
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        // 找到 nums 数组的中位数 nums[n/2]，n 为数组元素个数
        // 所有数往该中位数靠拢
        Arrays.sort(nums);
        int mid = nums[nums.length/2];

        int count = 0;
        for (int num : nums) {
            count += Math.abs(mid - num);
        }

        return count;
    }
}
