package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Question47 {
    public static void main(String[] args) {
        int [] nums = {1,1,2,2};
        System.out.println(permuteUnique(nums));
    }

    /**
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     *
     * 利用递归
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        if (n < 1) {
            return new ArrayList<>();
        }
        if (n == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[0]);
            List<List<Integer>> result = new ArrayList<>();
            result.add(list);
            return result;
        }

        List<List<Integer>> other = permuteUnique(Arrays.copyOfRange(nums, 1, n));
        HashSet<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < other.size(); i++) {
            List<Integer> curr = other.get(i);
            for (int j = 0; j <= curr.size(); j++) {
                List<Integer> list = new ArrayList<>(curr);
                if (j == 0 || (j > 0 && list.get(j-1) != nums[0])) {
                    list.add(j, nums[0]);
                    result.add(list);
                }
            }
        }

        return new ArrayList<>(result);
    }
}
