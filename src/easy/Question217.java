package easy;

import java.util.Arrays;
import java.util.HashSet;

public class Question217 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,3,3,4,3,2,4,2};
        System.out.println(new Question217().containsDuplicate(nums));
    }

    /**
     * 给定一个整数数组，判断是否存在重复元素。
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i])) {
                return true;
            } else {
                hashSet.add(nums[i]);
            }
        }

        return false;
    }

    /**
     * 排序，比上面的哈希快一点
     */
    public boolean containsDuplicate_sort(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] == nums[i+1]) {
                return true;
            }
        }

        return false;
    }
}
