package double_pointer;

import java.util.Arrays;

public class Question532 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        System.out.println(new Question532().findPairs(nums, 0));
    }

    /**
     * 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。
     * 这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，
     * 且两数之差的绝对值是 k.
     *
     * 注意:
     * 1. 数对 (i, j) 和数对 (j, i) 被算作同一数对。
     * 2. 数组的长度不超过10,000。
     * 3. 所有输入的整数的范围在 [-1e7, 1e7]。
     *
     * 双指针
     *
     * @param nums
     * @param k
     * @return
     */
    public int findPairs(int[] nums, int k) {
        if (k < 0 || nums.length <= 1) {
            return 0;
        }

        // 先排序
        Arrays.sort(nums);
        // 双指针，left < right，找到符合条件的(nums[left], nums[right])对
        int left = 0;
        int right = 1;
        int res = 0;
        while (left != -1 && right != -1 && right < nums.length) {
            System.out.println("left = " + left + ", right = " + right);
            if (nums[right] - nums[left] == k) {
                res++;
                // 找到下一不同的数
                left = findNext(nums, left);
                right = findNext(nums, right);
                // 若 left == right，right 后移
                if (left == right) {
                    right++;
                }
            } else if (nums[right] - nums[left] > k) {
                // 找到下一不同的 left
                left = findNext(nums, left);
                if (left == right) {
                    right++;
                }
            } else {
                // 找到下一不同的 right
                right = findNext(nums, right);
            }
        }

        return res;
    }

    /**
     * 找到下一不同的数，找不到则返回 -1
     */
    private int findNext(int[] nums, int curr) {
        for (int i = curr+1; i < nums.length; i++) {
            if (nums[i] != nums[curr]) {
                return i;
            }
        }

        return -1;
    }
}
