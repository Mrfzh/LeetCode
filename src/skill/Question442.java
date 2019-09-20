package skill;

import java.util.ArrayList;
import java.util.List;

public class Question442 {
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(new Question442().findDuplicates(nums));
    }

    /**
     * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
     *
     * 找到所有出现两次的元素。
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // 假如 nums[i] = a，那么将 nums[a-1] 的数置为负（取反）
        // 那么当再次出现该数时，对应位置已经为负，即说明这数已经出现过
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i] < 0? -nums[i] : nums[i];    // 保证该数为正
            if (nums[a - 1] < 0) {
                res.add(a);
            } else {
                nums[a - 1] = - nums[a - 1];    // 第一次出现，取反
            }
        }

        return res;
    }
}
