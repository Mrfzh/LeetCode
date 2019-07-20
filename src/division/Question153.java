package division;

public class Question153 {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(new Question153().findMin(nums));
    }

    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * 请找出其中最小的元素。
     * 你可以假设数组中不存在重复元素。
     *
     * 二分法
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right-1) {
            int mid = (left + right) / 2;
            if (nums[left] < nums[mid] && nums[right] < nums[mid]) {
                // 右半部分找
                left = mid + 1;
            } else if (nums[left] > nums[mid] && nums[right] > nums[mid]) {
                // 左半部分找（包括中点）
                right = mid;
            } else if (nums[left]< nums[mid] && nums[right] > nums[mid]) {
                // left 即为最小
                return nums[left];
            }
        }

        // 此时 left 可能等于 right，也可能等于 right-1
        if (left == right) {
            return nums[left];
        } else {
            return nums[left] < nums[right] ? nums[left] : nums[right];
        }
    }
}
