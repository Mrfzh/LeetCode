package hash;

import java.util.Arrays;
import java.util.HashMap;

public class Question496 {
    public static void main(String[] args) {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        int[] res = new Question496().nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
     * 找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
     *
     * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。
     * 如果不存在，对应位置输出-1。
     *
     * dp + hash
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums2.length == 0 || nums1.length == 0) {
            return new int[0];
        }

        // dp[a] 表示在 nums2 中第 a 个元素的下一个比其大的值的索引，若无则为 -1
        int[] dp = new int[nums2.length];
        dp[nums2.length-1] = -1;
        // HashMap 的 key 表示 nums2 中的元素值，value 为该值的下一个比其大的值，若无则为 -1
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(nums2[nums2.length-1], -1);
        // 从后往前遍历 nums2
        for (int i = nums2.length-2; i >= 0; i--) {
            int nextMaxIndex = i+1;
            int currNum = nums2[i];
            int nextMaxNum = nums2[nextMaxIndex];
            while (nextMaxNum < currNum) {
                nextMaxIndex = dp[nextMaxIndex];
                if (nextMaxIndex == -1) {
                    break;
                }
                nextMaxNum = nums2[nextMaxIndex];
            }
            if (nextMaxIndex == -1) {
                // 说明没有找到下一比其大的值
                dp[i] = -1;
                hashMap.put(currNum, -1);
            } else {
                // 找到下一比其大的值
                dp[i] = nextMaxIndex;
                hashMap.put(currNum, nextMaxNum);
            }
        }

        // 遍历 nums1，根据 hashMap 得出 res
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = hashMap.get(nums1[i]);
        }

        return res;
    }

    /**
     * 还有一种写法是借助栈，对于 nums2 数组，不断将元素入栈，如果发现比栈顶元素大，
     * 就说明栈顶元素的下一最大值是当前元素
     */
}
