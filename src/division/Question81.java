package division;

public class Question81 {
    public static void main(String[] args) {
        int[] nums = {2,5,6,7,9,11,-1,0,0,1,2};
        System.out.println(new Question81().search(nums, 0));
        System.out.println(new Question81().search(nums, -1));
        System.out.println(new Question81().search(nums, 8));
    }

    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2]
     * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
     *
     * 二分法
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n < 1) {
            return false;
        }

        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == nums[right]) {
                break;
            } else if (nums[mid] > nums[right]) {
                if (target == nums[mid]) {
                    return true;
                } else if (target > nums[mid]) {
                    left = mid + 1;
                } else {
                    if (target == nums[left]) {
                        return true;
                    } else if (target > nums[left]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            } else {
                if (target == nums[mid]) {
                    return true;
                } else if (target < nums[mid]) {
                    right = mid - 1;
                } else {
                    if (target == nums[right]) {
                        return true;
                    } else if (target > nums[right]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }

        if (left <= right) {
            for (int i = left; i <= right; i++) {
                if (nums[i] == target) {
                    return true;
                }
            }
        }

        return false;
    }
}
