package back;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Question491 {
    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        System.out.println(new Question491().findSubsequences(nums));
    }

    /**
     * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
     *
     * 注意：给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
     *
     * 回溯（递归）
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        HashSet<List<Integer>> res = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            find(nums, i, Integer.MIN_VALUE, new ArrayList<>(), res);
        }

        return new ArrayList<>(res);
    }

    /**
     * 从 nums[start] 开始查找，找到比 lastNum 大的数，curr 为当前集合, res 为输出结果
     */
    private void find(int[] nums, int start, int lastNum, List<Integer> curr,
                      HashSet<List<Integer>> res) {
        if (start >= nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (curr.isEmpty() || nums[start] >= lastNum) {
                List<Integer> newList = new ArrayList<>(curr);
                newList.add(nums[start]);
                if (newList.size() >= 2) {
                    res.add(newList);
                }
                find(nums, i+1, nums[start], newList, res);
            }
        }
    }
}
