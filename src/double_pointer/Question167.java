package double_pointer;

import java.util.Arrays;

public class Question167 {
    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }

    /**
     * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     *
     * 说明
     * 1. 返回的下标值（index1 和 index2）不是从零开始的。
     * 2. 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     *
     * 双指针
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int[] res = new int[2];

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                res[0] = left+1;
                res[1] = right+1;
                break;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }

        return res;
    }
}
