package division;

public class Question35 {
    public static void main(String[] args) {
        int [] nums = {1,3,5,6};
        System.out.println(searchInsert(nums, 2));
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 你可以假设数组中无重复元素。
     *
     * 二分法
     *
     * @param nums
     * @param target
     * @return
     */
    private static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        if (n < 1) {
            return 0;
        }

        int left = 0;
        int right = n - 1;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        } else if (nums[right] == target){
            return right;
        }

        //没找到的话
        if (target > nums[right]) {
            return ++right;
        } else if (target < nums[left]) {
            return left;
        } else {
            return right;
        }
    }
}
