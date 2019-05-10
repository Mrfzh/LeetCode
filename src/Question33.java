public class Question33 {
    public static void main(String[] args) {
        int [] nums = {4,5,6,7,0,1,2};
        System.out.println(search(nums, 3));
    }

    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * 假设数组中不存在重复的元素
     * 算法时间复杂度必须是 O(log n) 级别
     *
     * 利用二分法
     *
     * @param nums 给定的数组
     * @param target 目标值
     * @return 如果数组中存在这个目标值，则返回它的索引，否则返回 -1
     */
    private static int search(int[] nums, int target) {
        int n = nums.length;
        if (n < 1) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target? 0 : -1;
        }

        int left = 0;
        int right = n-1;
        while (right - left > 1) {
            int mid = (right + left) / 2;
            //分三种情况
            if (nums[mid] >= nums[left] && nums[mid] <= nums[right]) {  //全部是升
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid;
                } else {
                    right = mid;
                }
            } else if (nums[mid] < nums[left] && nums[mid] < nums[right]) { //原先的前i个数移到了后面，i > mid
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    if (target < nums[right]) {
                        left = mid;
                    } else if (target > nums[right]){
                        right = mid;
                    } else {
                        return right;
                    }
                } else {
                    right = mid;
                }
            }else if (nums[mid] > nums[left] && nums[mid] > nums[right]) { //同上，不过 i <mid
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid;
                } else {
                    if (target < nums[left]) {
                        left = mid;
                    } else if (target > nums[left]) {
                        right = mid;
                    } else {
                        return left;
                    }
                }
            }
        }

        if (nums[left] == target) {
            return left;
        } else if (nums[right] == target) {
            return right;
        } else {
            return -1;
        }
    }
}
