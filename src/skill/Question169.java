package skill;

import java.util.HashMap;

public class Question169 {
    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(new Question169().majorityElement_2(nums));
    }

    /**
     * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在众数。
     *
     * 哈希表法
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int target = nums.length / 2 + 1;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (hashMap.containsKey(num)) {
                int curr = hashMap.get(num) + 1;
                if (curr == target) {
                    return num;
                } else {
                    hashMap.replace(num, curr);
                }
            } else {
                hashMap.put(num, 1);
            }
        }
        return nums[0];
    }

    /**
     * 摩尔投票法，O(n) 时间复杂度，但只需 O(1) 空间复杂度
     */
    public int majorityElement_2(int[] nums) {
        int candidate = nums[0];    // 候选人
        int count = 0;              // 候选人票数
        for (int curr : nums) {
            if (curr == candidate) {
                count++;    // 候选人票数加 1（来了个自己人）
            } else {
                if (count == 0) {
                    candidate = curr;    // 新的候选人（敌方已经没人了）
                    count = 1;
                } else {
                    count--;    // 候选人票数减 1（和敌方的一人同归于尽）
                }
            }
        }

        // 最后留下来的就是最终赢家（兵力超过一半，其它人联合起来也不是对手）
        return candidate;
    }
}
