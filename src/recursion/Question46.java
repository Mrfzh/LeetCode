package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question46 {
    public static void main(String[] args) {
        int [] nums = {1,2,3};
        System.out.println(permute(nums));
    }

    /**
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。
     *
     * 利用递归
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if (n < 1) {
            result.add(new ArrayList<>());
            return result;
        }
        if (n == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[0]);
            result.add(list);
            return result;
        }

        List<List<Integer>> other = permute(Arrays.copyOfRange(nums, 1, n));
        for (int i = 0; i < other.size(); i++) {
            List<Integer> curr = other.get(i);
            for (int j = 0; j <= curr.size(); j++) {
                List<Integer> list = new ArrayList<>(curr);
                list.add(j, nums[0]);
                result.add(list);
            }
        }

        return result;
    }
}
