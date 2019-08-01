package double_pointer;

public class Question209 {
    public static void main(String[] args) {
        int[] nums = {10, 2, 3};
        System.out.println(new Question209().minSubArrayLen_double_pointer(6, nums));
    }

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，
     * 找出该数组中满足其和 ≥ s 的长度最小的连续子数组。
     * 如果不存在符合条件的连续子数组，返回 0。
     *
     * 动态规划
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int min = 0;
        int last = 0;   // 记录从上一个数开始，符合条件的连续子数组长度
        int lastRes = 0;

        // 计算从第一个数开始的符合条件的连续子数组长度
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum >= s) {
                last = i + 1;
                lastRes = sum;
                min = last;
                break;
            }
        }

        // 计算从第二个数开始的符合条件的连续子数组长度
        for (int i = 1; last != 0 && i <= nums.length + 1 - last; i++) {
            // 可能不需要上一个数就满足
            if (lastRes - nums[i-1] >= s) {
                last--;
                lastRes -= nums[i-1];
                if (min > last) {
                    min = last;
                }
            }
            // 还不满足就需要加上后面的数了
            else {
                sum = lastRes - nums[i-1];
                boolean hasFound = false;
                for (int j = i+last-1; j < nums.length; j++) {
                    sum += nums[j];
                    if (sum >= s) {
                        last = j - i + 1;   // 不要忘了加 1
                        lastRes = sum;
                        if (min > last) {
                            min = last;
                        }
                        hasFound = true;
                        break;
                    }
                }
                // 如果遍历后没有找到就退出循环
                if (!hasFound) {
                    break;
                }
            }
        }

        return min;
    }

    /**
     * 双指针法
     */
    public int minSubArrayLen_double_pointer(int s, int[] nums) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        // 外层循环：向右移动 right 指针，使得子数组之和满足条件
        while (right < nums.length) {
            sum += nums[right];
            // 里层循环：当 right 指针移动到合适位置时，向右移动 left 指针
            // 尽可能地让子数组的长度最小
            while (left <= right && sum >= s) {
                min = Math.min(min, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
