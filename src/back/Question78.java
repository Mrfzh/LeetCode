package back;

import java.util.ArrayList;
import java.util.List;

public class Question78 {
    public static void main(String[] args) {
        Question78 question78 = new Question78();
        int [] nums = {1,2,3};
        System.out.println(question78.subsets(nums));
    }

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 说明：解集不能包含重复的子集。
     *
     * 回溯法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        result.add(new ArrayList<>());
        if (nums.length < 1) {
            return result;
        }

        for (int i = 1; i <= nums.length; i++) {
            back(new ArrayList<>(), nums, i, 0);
        }

        return result;
    }

    /**
     * 从数组的start索引开始，求n个元素的组合
     *
     * @param curr
     * @param nums
     * @param n
     * @param start
     */
    private void back(List<Integer> curr, int [] nums, int n, int start) {
        if (curr.size() == n) {
            result.add(curr);
            return;
        }

        for (int i = start; i < nums.length - (n - curr.size()) + 1; i++) {
            List<Integer> list = new ArrayList<>(curr);
            list.add(nums[i]);
            back(list, nums, n, i + 1);
        }
    }
}
