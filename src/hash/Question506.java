package hash;

import java.util.Arrays;
import java.util.HashMap;

public class Question506 {
    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(new Question506().findRelativeRanks(nums)));
    }

    /**
     * 给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。
     * 前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”
     * （"Gold Medal", "Silver Medal", "Bronze Medal"）。
     *
     * (注：分数越高的选手，排名越靠前。)
     *
     * 提示:
     * 1. N 是一个正整数并且不会超过 10000。
     * 2. 所有运动员的成绩都不相同。
     *
     * @param nums
     * @return
     */
    public String[] findRelativeRanks(int[] nums) {
        // HashMap 的 key 存储成绩，value 存储其在 nums 数组的索引
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], i);
        }

        // 排序
        Arrays.sort(nums);

        String[] res = new String[nums.length];
        // 先排前三名
        for (int i = nums.length-1; i >= 0 && i >= nums.length-3; i--) {
            if (i == nums.length-1) {
                res[hashMap.get(nums[i])] = "Gold Medal";
            } else if (i == nums.length-2) {
                res[hashMap.get(nums[i])] = "Silver Medal";
            } else {
                res[hashMap.get(nums[i])] = "Bronze Medal";
            }
        }
        // 排后面的
        for (int i = nums.length-4; i >= 0; i--) {
            res[hashMap.get(nums[i])] = String.valueOf(nums.length - i);
        }

        return res;
    }
}
