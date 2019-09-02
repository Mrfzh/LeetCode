package skill;

import java.util.Random;

/**
 * 打乱一个没有重复元素的数组。
 *
 * 洗牌算法
 */
public class Question384 {
    private int[] original;     // 原始数组
    private Random random = new Random();

    public Question384(int[] nums) {
        original = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original.clone();
    }

    /**
     * 获得 [start, end) 中的一个随机数
     */
    private int randRange(int start, int end) {
        return random.nextInt(end - start) + start;
    }

    /**
     * 交换 nums[i] 和 nums[j]
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] res = original.clone();
        int n = res.length;
        for (int i = 0; i < n; i++) {
            // 交换当前元素和 [i, n) 中随机数所对应的元素
            swap(res, i, randRange(i, n));
        }

        return res;
    }
}
