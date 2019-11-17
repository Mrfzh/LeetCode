package recursion;

import java.util.ArrayList;
import java.util.List;

public class Question698 {
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        System.out.println(new Question698().canPartitionKSubsets(nums, 5));
    }

    /**
     * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
     *
     * 注意:
     * 1 <= k <= len(nums) <= 16
     * 0 < nums[i] < 10000
     *
     * 第一天：148 / 149 个通过测试用例 o(╥﹏╥)o
     * 第二天：重新理了下思路，用了“两个递归 + bit记忆化搜索 ”做了出来，执行用时 2ms，还不错
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // 首先判断是否可以均分为 k 个非空子集，若可以的话求出子集的和 n
        int n;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        } else {
            n = sum / k;
        }
        for (int num : nums) {
            if (num > n) {
                return false;
            }
        }

        return helper(nums, k, n, 0);
    }

    /**
     * 当前 nums 数组是否能分成 k 个不同子集，每个子集的和为 n
     * bit 表示已有组合（由低到高表示 nums[0 ... n-1] 是否被使用，被使用的话为 1）
     */
    private boolean helper(int[] nums, int k, int n, int bit) {
        if (k == 1) {
            return true;
        }
        for (int i = 0; i < nums.length; i++) {
            if (((1 << i) & bit) == 0) {
                // 表示 num[i] 未使用
                List<Integer> res = new ArrayList<>();
                find(nums, i, bit, n, res);
                if (res.isEmpty()) {
                    return false;
                } else {
                    for (int b : res) {
                        if (helper(nums, k-1, n, b)) {
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 从 nums[start] 开始查找，是否能找到和满足 n 的子集
     * 如果能找到的话将现有组合添加到 res，bit 表示已有组合
     */
    private void find(int[] nums, int start, int bit, int n, List<Integer> res) {
        if (nums[start] > n || ((1 << start) & bit) != 0) {
            return;
        }
        bit = bit | (1 << start);
        if (nums[start] == n) {
            res.add(bit);
            return;
        }
        for (int i = start + 1; i < nums.length; i++) {
            find(nums, i, bit, n - nums[start], res);
        }
    }
}
