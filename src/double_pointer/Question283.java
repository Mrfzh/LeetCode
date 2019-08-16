package double_pointer;

import java.util.Arrays;

public class Question283 {
    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        new Question283().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，
     * 同时保持非零元素的相对顺序。
     *
     * 说明:
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     *
     * 双指针
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int curr = 0;
        // 从前往后遍历，依次放置不为 0 的元素
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[curr++] = nums[i];
            }
        }
        // 将 0 放到末尾
        for (int i = curr; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
