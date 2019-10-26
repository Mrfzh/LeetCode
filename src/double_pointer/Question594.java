package double_pointer;

import java.util.Arrays;

public class Question594 {
    public static void main(String[] args) {
        int[] nums = {1,3,2,2,5,2,3,7};
        System.out.println(new Question594().findLHS_simplify(nums));
    }

    /**
     * 和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。
     *
     * 现在，给定一个整数数组，你需要在所有可能的子序列中找到最长的和谐子序列的长度。
     *
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 先排序
        Arrays.sort(nums);

        int res = 0;
        int lastNum = nums[0];
        int lastCount = 0;
        int currNum = nums[0];
        int currCount = 1;
        int i;
        for (i = 1; i < nums.length; i++) {
            if (nums[i] == currNum) {
                currCount++;
            } else {
                break;
            }
        }
        for (int j = i; j < nums.length; j++) {
            if (nums[j] != currNum) {
                // 看上两个数之间的差是否为 1，是的话就加起来
                if (currNum - lastNum == 1) {
                    res = Math.max(lastCount + currCount, res);
                }
                // 更新相关变量
                lastNum = currNum;
                lastCount = currCount;
                currNum = nums[j];
                currCount = 1;
            } else {
                currCount++;
            }
        }
        // 最后还要再检查一次
        if (currNum - lastNum == 1) {
            res = Math.max(lastCount + currCount, res);
        }

        return res;
    }

    /**
     * 简化：排序 + 双指针
     */
    public int findLHS_simplify(int[] nums) {
        Arrays.sort(nums);
        int start = 0;
        int res = 0;
        for (int end = 0 ; end < nums.length; end++) {
            while (nums[end] - nums[start] > 1) {
                start++;
            }
            // 发现相差的值为一就更新 res
            if (nums[end] - nums[start] == 1) {
                res = Math.max(res, end - start + 1);
            }
        }

        return res;
    }
}
