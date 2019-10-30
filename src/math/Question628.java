package math;

import java.util.Arrays;

public class Question628 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(new Question628().maximumProduct(nums));
    }

    /**
     * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
     *
     * 注意:
     * 给定的整型数组长度范围是[3,10^4]，数组中所有的元素范围是[-1000, 1000]。
     * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
     *
     * 排序 + 数学
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        // 先排序
        Arrays.sort(nums);

        // 最大的乘积可能是以下两者之一：
        // （1）前两个数 + 最后一个数
        // （2）最后三个数
        return Math.max(nums[0] * nums[1] * nums[nums.length-1],
                nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);
    }
}
