package easy;

public class Question747 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(new Question747().dominantIndex(nums));
    }

    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     *
     * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     * 如果是，则返回最大元素的索引，否则返回-1。
     *
     * 提示:
     * nums 的长度范围在[1, 50].
     * 每个 nums[i] 的整数范围在 [0, 100].
     *
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {
        // 查找第一大和第二大的元素
        int max = -1;
        int maxIndex = -1;
        int secMax = -1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > max) {
                secMax = max;
                max = num;
                maxIndex = i;
            } else if (num > secMax && num < max) {
                secMax = num;
            }
        }

        if (secMax < 0) {   // 所有数都相等
            return 0;
        } else if (secMax == 0) {   // 第二小是 0 时，最大值有效
            return maxIndex;
        } else {
            return (max / secMax) >= 2? maxIndex : -1;
        }
    }
}
