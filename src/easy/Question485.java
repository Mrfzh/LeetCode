package easy;

public class Question485 {
    public static void main(String[] args) {
        int[] nums = {1,1,0,1,1,1};
        System.out.println(new Question485().findMaxConsecutiveOnes(nums));
    }

    /**
     * 给定一个二进制数组， 计算其中最大连续1的个数。
     *
     * 注意：
     * 1. 输入的数组只包含 0 和1。
     * 2. 输入数组的长度是正整数，且不超过 10,000。
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int curr = 0;   // 当前连续 1 的个数
        for (int num : nums) {
            if (num == 0) {
                curr = 0;
            } else {
                curr++;
                max = Math.max(max, curr);
            }
        }

        return max;
    }
}
