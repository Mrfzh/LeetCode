package bit;

public class Question268 {
    public static void main(String[] args) {
        int[] nums = {9,6,4,2,3,5,7,0,1};
        System.out.println(new Question268().missingNumber_bit(nums));
    }

    /**
     * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，
     * 找出 0 .. n 中没有出现在序列中的那个数。
     *
     * 说明:
     * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        long target = nums.length * (nums.length+1) / 2;
        for (int num : nums) {
            target -= num;
        }
        return (int) target;
    }

    /**
     * 方法二：位运算(利用异或的性质：n ^ n = 0，0 ^ n = n)
     */
    public int missingNumber_bit(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }
}
