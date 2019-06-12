package back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question90 {

    private List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        int [] nums = {1,2,2};
        System.out.println(new Question90().subsetsWithDup(nums));
    }

    /**
     * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 说明：解集不能包含重复的子集。
     *
     * 回溯
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        result.add(new ArrayList<>());

        Arrays.sort(nums);  //先排序
        for (int i = 1; i <= nums.length; i++) {
            back(new ArrayList<>(), nums, 0, i);
        }

        return result;
    }

    /**
     *
     * @param curr 当前组合
     * @param nums 数组
     * @param start 下一元素的开始遍历位置
     * @param n 需要的元素总数
     */
    private void back(List<Integer> curr, int [] nums, int start, int n) {
        if (curr.size() == n) {
            result.add(curr);
            return;
        }

        for (int i = start; i <= nums.length - (n - curr.size()); i++) {
            //去重
            if(i > start && nums[i] == nums[i-1]){
                continue;
            }

            List<Integer> list = new ArrayList<>(curr);
            list.add(nums[i]);
            back(list, nums, i+1, n);
        }
    }
}
