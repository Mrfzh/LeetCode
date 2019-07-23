package hash;

import java.util.HashMap;

public class Question169 {
    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(new Question169().majorityElement(nums));
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
}
