package skill;

public class Question334 {
    public static void main(String[] args) {
        int[] nums = {5,4,3,2,1};
        System.out.println(new Question334().increasingTriplet(nums));
    }

    /**
     * 给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。
     *
     * 数学表达式如下:
     * 如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
     * 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
     *
     * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        int small = nums[0];    // 第一小
        int secondSmall = 0;    // 第二小
        int tempSmall = Integer.MAX_VALUE;  // 暂存比第一小更小的数

        int i;
        boolean hasFound = false;
        for (i = 1; i < nums.length; i++) {
            if (nums[i] > small) {
                secondSmall = nums[i];
                hasFound = true;
                break;
            } else if (nums[i] < small) {
                small = nums[i];
            }
        }
        if (!hasFound) {
            return false;
        }

        boolean hasTempSmall = false;
        for (int j = i+1; j < nums.length; j++) {
            if (nums[j] > secondSmall) {
                return true;
            } else if (nums[j] < secondSmall && nums[j] > small) {
                secondSmall = nums[j];
            } else if (nums[j] < small) {
                if (!hasTempSmall) {
                    tempSmall = nums[j];
                    hasTempSmall = true;
                } else {
                    if (nums[j] < tempSmall) {
                        tempSmall = nums[j];
                    } else if (nums[j] > tempSmall) {
                        small = tempSmall;
                        secondSmall = nums[j];
                        hasTempSmall = false;
                    }
                }
            }
        }

        return false;
    }
}
