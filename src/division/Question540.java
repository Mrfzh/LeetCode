package division;

public class Question540 {
    public static void main(String[] args) {
        int[] nums = {3,3,7,7,10,11,11};
        System.out.println(new Question540().singleNonDuplicate(nums));
    }

    /**
     * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
     *
     * 注意: 您的方案应该在 O(log n)时间复杂度和 O(1)空间复杂度中运行。
     *
     * 二分法
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }

        int left = 0;
        int right = nums.length-1;
        while (left < right) {
            int mid = left + (right - left)/2;
            // 如果 nums[mid] 和左右两边的数都不同，说明 nums[mid] 即为所找的数
            if (nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]) {
                return nums[mid];
            }
            // 否则分两种情况：
            // 1. 左右两侧有奇数个数时，继续在与 nums[mid] 不等的那一侧遍历
            // 2. 左右两侧有偶数个数时，继续在与 nums[mid] 相等的那一侧遍历
            if ((mid - left) % 2 == 1) {
                if (nums[mid] != nums[mid-1]) {
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            } else {
                if (nums[mid] != nums[mid-1]) {
                    left = mid+2;
                } else {
                    right = mid-2;
                }
            }
        }
        // 退出循环时，left == right，返回其中一个对应的数即可
        return nums[left];
    }
}
