package bit;

import java.util.Arrays;

public class Question136 {
    public static void main(String[] args) {
        int[] nums = {2,2,1};
        System.out.println(new Question136().singleNumber_bit(nums));
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        //先排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i+=2) {
            if (nums[i] != nums[i+1]) {
                return nums[i];
            }
        }

        return nums[nums.length-1];
    }

    /**
     * 优化：利用位运算（异或）
     * 由于 0 ^ a = a, a ^ a = 0, 且异或运算满足交换律
     * 所以只要把数组中的所有元素进行异或操作后，得到的结果就是只出现了一次的元素
     */
    public int singleNumber_bit(int[] nums) {
        int a = nums[0];
        for (int i = 1; i < nums.length; i++) {
            a ^= nums[i];
        }

        return a;
    }
}
