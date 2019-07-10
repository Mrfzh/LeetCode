package bit;

import java.util.Arrays;

public class Question137 {
    public static void main(String[] args) {
        int[] nums = {0,1,0,1,0,1,99};
        System.out.println(new Question137().singleNumber_bit_simply(nums));
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。
     * 找出那个只出现了一次的元素。
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        //先排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i+=3) {
            if (nums[i] != nums[i+1]) {
                return nums[i];
            }
        }

        return nums[nums.length - 1];
    }

    /**
     * 优化：位运算
     */
    public int singleNumber_bit(int[] nums) {
        //对于32位的int：某位为1说明该位出现1（2,3）次
        int one = 0;
        int two = 0;
        int three = 0;

        for(int num : nums) {
            two |= one & num;
            one ^= num;
            three = one & two;
            //当某位出现3次时，one和two的对应位要归零
            one &= ~three;
            two &= ~three;
        }

        return one;
    }

    /**
     * 更加简化的位运算
     */
    public int singleNumber_bit_simply(int[] nums) {
        //对于32位的int：某位为1说明该位出现1（2）次
        int one = 0;
        int two = 0;

        for(int num : nums) {
            one = (one ^ num) & ~two;
            two = (two ^ num) & ~one;
        }

        return one;
    }
}
