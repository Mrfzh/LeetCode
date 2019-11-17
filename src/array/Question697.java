package array;

public class Question697 {
    public static void main(String[] args) {
        int[] nums = {1,2,2,3,1,4,2};
        System.out.println(new Question697().findShortestSubArray(nums));
    }

    /**
     * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
     *
     * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
     *
     * 注意:
     * nums.length 在1到50,000区间范围内。
     * nums[i] 是一个在0到49,999范围内的整数。
     *
     * 数组
     *
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {
        // 找到数组的最大值，用于之后开辟数组
        int max = nums[0];
        for (int num : nums) {
            max = Math.max(num, max);
        }
        // head[a] 表示 a 在数组中第一次出现的位置
        int[] head = new int[max+1];
        // tail[a] 表示 a 在数组中最后一次出现的位置
        int[] tail = new int[max+1];
        // n[a] 表示 a 在数组中出现的次数
        int[] n = new int[max+1];
        // 计算数组的度
        int point = 0;

        for (int i = 0; i < nums.length; i++) {
            n[nums[i]]++;
            if (n[nums[i]] == 1) {
                head[nums[i]] = tail[nums[i]] = i;
            } else {
                tail[nums[i]] = i;
            }
            point = Math.max(point, n[nums[i]]);
        }
        // 得到结果
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n.length; i++) {
            if (n[i] == point) {
                res = Math.min(res, tail[i] - head[i] + 1);
            }
        }

        return res;
    }
}
