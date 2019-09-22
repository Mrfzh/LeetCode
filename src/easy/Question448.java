package easy;

import java.util.ArrayList;
import java.util.List;

public class Question448 {
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(new Question448().findDisappearedNumbers(nums));
    }

    /**
     * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，
     * 数组中的元素一些出现了两次，另一些只出现一次。
     *
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // 对于出现过的数 a（不管一次还是两次），将 a-1 索引处的数取负
        for (int i = 0; i < nums.length; i++) {
            int a = Math.abs(nums[i]);
            if (nums[a-1] > 0) {
                nums[a-1] = -nums[a-1];
            }
        }
        // 遍历数组元素，假设元素索引为 i，当元素为正，说明 i+1 没有出现过
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i+1);
            }
        }

        return res;
    }
}
