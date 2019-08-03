package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Question219 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1,2,3};
        System.out.println(new Question219().containsNearbyDuplicate_better(nums, 2));
    }

    /**
     * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
     * 使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }

        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        // key 为元素值，value 为该元素对应的索引

        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                // 查找是否有两者索引的差值 <= k
                List<Integer> list = hashMap.get(nums[i]);
                if (Math.abs(list.get(list.size() - 1) - i) <= k) {
                    return true;
                } else {
                    list.add(i);
                }
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                hashMap.put(nums[i], list);
            }
        }

        return false;
    }

    /**
     * 优化：不用 HashMap，用 HashSet 即可
     */
    public boolean containsNearbyDuplicate_better(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }

        HashSet<Integer> hashSet = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i])) {
                return true;
            }
            hashSet.add(nums[i]);
            // 关键：只保留最近的 k 个数，前面无效的数移除出 HashSet
            if (hashSet.size() > k) {
                hashSet.remove(nums[i - k]);
            }
        }

        return false;
    }
}
