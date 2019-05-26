package double_pointer;

import java.util.Arrays;

public class Question80 {
    public static void main(String[] args) {
        int [] nums = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * 注意：原数组的前k（返回值）个元素为新数组的所有元素
     *
     * 双指针法
     *
     * @param nums 给定数组
     * @return  新数组的元素个数
     */
    private static int removeDuplicates(int[] nums) {
        int n = nums.length;    //元素个数
        if (n < 3) {
            return n;
        }

        int i = 0;
        int repeat = 1; //记录前一个元素的重复个数
        for (int j = 1; j < n; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
                repeat = 1;
            } else {
                if (repeat < 2) {
                    i++;
                    nums[i] = nums[j];
                }
                repeat++;
            }
        }

        return i+1;
    }
}
