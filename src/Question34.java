import java.util.Arrays;

public class Question34 {
    public static void main(String[] args) {
        int [] nums = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(nums, 10)));
    }

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * 如果数组中不存在目标值，返回 [-1, -1]。
     *
     * 二分法
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        if (n < 1) {
            return new int [] {-1, -1};
        }

        int left = 0;
        int right = n - 1;
        int found = -1;     //找到数组中元素为target的索引
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                found = mid;
                break;
            } else if (nums[mid] > target){
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) {
            found = left;
        } else if (nums[right] == target) {
            found = right;
        }

        int [] result = {-1, -1};
        if (found != -1) {
            //从found两边开始搜索
            for (int i = found-1; i >= 0; i--) {
                if (nums[i] == target) {
                    result[0] = i;
                } else {
                    break;
                }
            }
            for (int i = found+1; i < n; i++) {
                if (nums[i] == target) {
                    result[1] = i;
                } else {
                    break;
                }
            }
            //后续处理
            if (result[0] == -1 && result[1] == -1) {   //两边都没有target
                result[0] = found;
                result[1] = found;
            } else if (result[0] == -1) {   //左边没找到
                result[0] = found;
            } else if (result[1] == -1) {   //右边没找到
                result[1] = found;
            }
        }

        return result;
    }
}
