package double_pointer;

import java.util.Arrays;

public class Question26 {
    public static void main(String[] args) {
        int [] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates_doublePoint(nums));
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 并且原数组 nums 的前k(k为返回值)个元素被修改为不重复的元素，后面的元素不用管。
     *
     * @param nums 给定数组
     * @return 移除后数组的新长度
     */
    private static int removeDuplicates(int[] nums) {
        //数组长度
        int n = nums.length;

        if (n < 2) {
            return n;
        }

        int pre = nums[0];    //保存前一个数
        for (int i = 1; i < n; ) {
            if (nums[i] == pre) {   //如果和前一个数相同
                //把后面的数移上来
                for (int j = i; j < n-1; j++) {
                    nums[j] = nums[j+1];
                }
                //数组总数减一
                n--;
            } else {
                pre = nums[i];
                i++;
            }
        }

        return n;
    }

    /**
     * 双指针法
     */
    private static int removeDuplicates_doublePoint(int[] nums) {
        //数组长度
        int n = nums.length;

        if (n < 2) {
            return n;
        }

        int i = 0;  //i指向新数组的最后一个元素
        for (int j = 1; j < n; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];  //num[j]加入新数组
            }
        }

        return i+1;
    }
}
