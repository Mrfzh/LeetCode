package easy;

import java.util.ArrayList;
import java.util.List;

public class Question228 {
    public static void main(String[] args) {
        int[] nums = {0,2,3,4,6,8,9};
        System.out.println(new Question228().summaryRanges(nums));
    }

    /**
     * 给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        int start = nums[0];    // 记录下一个区间的开头
        for (int i = 1; i < nums.length; i++) {
            // 如果两者相差不为 1，说明区间不相连
            if (nums[i] - nums[i-1] != 1) {
                if (start == nums[i-1]) {
                    res.add(String.valueOf(nums[i-1]));
                } else {
                    res.add(start + "->" + nums[i-1]);
                }
                start = nums[i];
            }
        }
        // 还有添加末尾元素
        if (start == nums[nums.length-1]) {
            res.add(String.valueOf(nums[nums.length-1]));
        } else {
            res.add(start + "->" + nums[nums.length-1]);
        }

        return res;
    }
}
