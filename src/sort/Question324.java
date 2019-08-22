package sort;

import java.util.Arrays;

public class Question324 {
    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 1, 6, 4};
        new Question324().wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
     *
     * 说明:
     * 你可以假设所有输入都会得到有效的结果。
     *
     * 进阶:
     * 你能用 O(n) 时间复杂度 或 原地 O(1) 额外空间来实现吗？
     * 做不到进阶（题解也没找到合适的），只能做到 O(nlogn) 时间复杂度和 O(n) 空间复杂度
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

        // 先排序
        Arrays.sort(nums);
        // 复制一份
        int[] copy = Arrays.copyOf(nums, nums.length);
        // 前 (n-1)/2 个数逆序后作为低谷，后面的数逆序后作为高峰
        int index = 0;
        for (int i = (copy.length-1)/2; i >= 0; i--) {
            nums[index] = copy[i];
            index += 2;
        }
        index = 1;
        for (int i = copy.length-1; i > (copy.length-1)/2; i--) {
            nums[index] = copy[i];
            index += 2;
        }
    }
}
