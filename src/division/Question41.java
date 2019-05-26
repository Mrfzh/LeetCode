package division;

import java.util.Arrays;

public class Question41 {
    public static void main(String[] args) {
        int [] nums = {7,8,9,11,12};
        System.out.println(firstMissingPositive(nums));
    }

    /**
     * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数(1,2,3,...)。
     *
     * 利用了二分法
     *
     * @param nums
     * @return
     */
    private static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        if (n < 1) {
            return 1;
        }

        Arrays.sort(nums);  //先排序
        if (nums[0] > 0) {
            //从第一个数开始，找出最小的正整数
            return find(nums, 0);
        } else if (nums[n-1] <= 0) {
            //全部数都小于等于0
            return 1;
        }else {
            //找到第一个正整数
            int left = 0;       //nums[left] <= 0
            int right = n-1;    //nums[right] > 0
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if (nums[mid] > 0) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            //最终nums[right]是第一个正整数
            return find(nums, right);
        }
    }

    private static int find(int[] nums, int start) {
        if (nums[start] != 1) {
            return 1;
        }
        int last = nums[start];
        for (int i = start+1; i < nums.length; i++) {
            int curr = nums[i];
            if (curr - last > 1) {
                return last + 1;
            } else {
                last = curr;
            }
        }

        return last + 1;
    }
}
