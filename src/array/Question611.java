package array;

import java.util.Arrays;

public class Question611 {
    public static void main(String[] args) {
        int[] nums = {2,2,3,4};
        System.out.println(new Question611().triangleNumber_better(nums));
    }

    /**
     * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
     *
     * 注意:
     * 数组长度不超过1000。
     * 数组里整数的范围为 [0, 1000]。
     *
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        // 先对数组排序
        Arrays.sort(nums);
        // 统计结果
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i+1; j < nums.length - 1; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    // 要组成三角形，需满足 nums[i] + nums[j] > nums[k]
                    if (nums[i] + nums[j] <= nums[k]) {
                        break;
                    }
                    res++;
                }
            }
        }

        return res;
    }

    /**
     * 优化：O(n^2) 的解法，外层循环选出较大边，内层循环选出两个较小边
     */
    public int triangleNumber_better(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        // 先对数组排序
        Arrays.sort(nums);
        // 统计结果
        int res = 0;
        for (int i = 2; i < nums.length; i++) {
            int left = 0;
            int right = i-1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    // nums[left] 到 nums[right-1] 和 nums[right] 加起来都会大于 nums[i]
                    res += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }

        return res;
    }
}
