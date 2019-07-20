package division;

public class Question162 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,3,5,6,4};
        System.out.println(new Question162().findPeakElement(nums));
    }

    /**
     * 峰值元素是指其值大于左右相邻值的元素。
     * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
     * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
     * 你可以假设 nums[-1] = nums[n] = -∞。
     *
     * 二分法
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            // 保证 nums[left] 比左边元素大，nums[right] 比右边元素大
            if (nums[mid] > nums[mid+1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // 最后 left 和 right 相等，该处的值即为峰值
        return right;
    }
}
