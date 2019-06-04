package three_pointer;

import java.util.Arrays;

public class Question75 {
    public static void main(String[] args) {
        int [] nums = {0,0,1,1,2,2};
        sortColors_threePointer(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，
     * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     *
     * @param nums
     */
    private static void sortColors(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }

        int zeroNum = 0;
        int oneNum = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroNum++;
            } else if (nums[i] == 1) {
                oneNum++;
            }
        }

        for (int i = 0; i < zeroNum; i++) {
            nums[i] = 0;
        }
        for (int i = zeroNum; i < zeroNum + oneNum; i++) {
            nums[i] = 1;
        }
        for (int i = zeroNum + oneNum; i < n; i++) {
            nums[i] = 2;
        }
    }

    /**
     * 三指针法
     */
    private static void sortColors_threePointer(int[] nums) {
        if (nums.length < 1) {
            return;
        }

        int p1 = 0;     //p1指针前的元素都是0
        int p2 = nums.length - 1;   //p2指针后的元素都是1
        int curr = 0;   //记录当前元素

        while (curr <= p2) {
            //当前为0或1时，curr要向后移一位
            //为2时不用，因为此时从后面交换来的数是没有遍历过的
            if (nums[curr] == 0) {
                swap(nums, curr, p1);
                p1++;
                curr++;
            } else if (nums[curr] == 2) {
                swap(nums, curr, p2);
                p2--;
            } else {
                curr++;
            }
        }
    }

    private static void swap(int [] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
