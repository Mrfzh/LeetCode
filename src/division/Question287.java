package division;

public class Question287 {
    public static void main(String[] args) {
        int[] nums = {3,1,3,4,2};
        System.out.println(new Question287().findDuplicate(nums));
    }

    /**
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
     * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     *
     * 说明：
     * 1. 不能更改原数组（假设数组是只读的）。
     * 2. 只能使用额外的 O(1) 的空间。
     * 3. 时间复杂度小于 O(n^2) 。
     * 4. 数组中只有一个重复的数字，但它可能不止重复出现一次。
     *
     * 二分
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        // 二分法：对 [1, 2, 3, ..., n] 进行二分
        int left = 1;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left)/2;
            // 遍历数组，查找等于和小于 mid 的元素个数
            int midCount = 0;
            int count = 0;
            for (int num : nums) {
                if (num < mid) {
                    count++;
                } else if (num == mid) {
                    midCount++;
                }
            }
            // 如果 mid 即为重复元素，直接返回
            if (midCount > 1) {
                return mid;
            }
            // 如果小于 mid 的元素个数大于 mid-1，说明重复元素小于 mid
            if (count > mid - 1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
