package hash;

import java.util.HashMap;

public class Question560 {
    public static void main(String[] args) {
        int[] nums = {1,1,1};
        System.out.println(new Question560().subarraySum_hash(nums, 2));
    }

    /**
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        // record[a] 记录上一次遍历以 nums[a] 开头的连续数组的和
        int[] record = new int[nums.length];
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= nums.length - i; j++) {
                record[j] += nums[j+i-1];
                if (record[j] == k) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 优化：利用 hash 记录累加和
     */
    public int subarraySum_hash(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        // key 为累加和，value 为该和出现次数（因为有负数，所以一个和可能出现多次）
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 1);  // 这个很关键，没有这个的话会漏了累加和为 k 的连续数组
        for (int num : nums) {
            sum += num;
            if (hashMap.containsKey(sum - k)) {
                // 如果前面有累加和为 sum - k，说明存在一段连续数组的和为 k
                count += hashMap.get(sum - k);
            }
            hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
