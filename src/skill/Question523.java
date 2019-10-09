package skill;

import java.util.HashMap;

public class Question523 {
    public static void main(String[] args) {
        int[] nums = {0,0};
        System.out.println(new Question523().checkSubarraySum_better(nums, -1));
    }

    /**
     * 给定一个包含非负数的数组和一个目标整数 k，编写一个函数来判断该数组是否含有连续的子数组，
     * 其大小至少为 2，总和为 k 的倍数，即总和为 n * k，其中 n 也是一个整数。
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        // 对 k 取绝对值
        k = Math.abs(k);
        if (nums.length == 0) {
            return false;
        }

        // "连续"是关键，问题转化为怎么快捷地求连续数组的和
        // dp[a] 表示 nums[0 ... a] 之间的和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i-1] + nums[i];
            if (isValid(dp[i], k)) {
                return true;
            }
        }

        // i 为起点，j 为终点，计算连续数组的和，看它十分满足条件
        for (int i = 1; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                // 计算 nums[i ... j] 的和
                int sum = dp[j] - dp[i-1];
                if (isValid(sum, k)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 检查连续数组的和 sum 是否满足条件
     */
    private boolean isValid(int sum, int k) {
        if (k == 0) {
            return sum == 0;
        }
        return sum % k == 0;
    }

    /**
     * 优化：O(n) 时间复杂度解法
     */
    public boolean checkSubarraySum_better(int[] nums, int k) {
        // 假设存在连续数组 n(a ... b]，其和为 k 的倍数，
        // 即 sum[a]（n[0 ... a] 的总和）和 sum[b] 的差为 k 的倍数
        // 那么应满足 sum[a] % k == sum[b] % k 且 b - a >= 2

        // 对于 k == 0 的情况，要特殊处理
        if (k == 0) {
            // 如果存在两个连续的 0，则满足条件
            boolean hasZero = false;
            for (int num : nums) {
                if (num == 0) {
                    if (hasZero) {
                        return true;
                    } else {
                        hasZero = true;
                    }
                } else {
                    hasZero = false;
                }
            }
            return false;
        }
        // HashMap 的 key 记录数组总和 sum % k 的值，value 记录 sum 的索引
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        // 这句是关键，因为可能出现 [1,2] 3 的情况，这时需要返回 true
        hashMap.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = (sum + nums[i]) % k;
            // 如果之前已经存在相同的 sum % k，并且当前索引值至少比它大 2
            // 说明存在连续数组满足条件
            if (hashMap.containsKey(sum)) {
                if (i - hashMap.get(sum) >= 2) {
                    return true;
                }
            } else {
                hashMap.put(sum, i);
            }
        }

        return false;
    }
}
