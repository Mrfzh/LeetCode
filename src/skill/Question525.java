package skill;

import java.util.HashMap;

public class Question525 {
    public static void main(String[] args) {
        int[] nums = {0,1,0,1,0,0};
        System.out.println(new Question525().findMaxLength_better(nums));
    }

    /**
     * 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。
     *
     * 注意: 给定的二进制数组的长度不会超过50000。
     *
     * 用数组辅助记录，做好剪枝
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        // record[a] 表示 nums[0 ... a) 中 0 的数量
        int[] record = new int[nums.length+1];
        record[0] = 0;
        int zeroNum = 0;
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i-1] == 0) {
                zeroNum++;
            }
            record[i] = zeroNum;
        }

        // 从第一个数开始，查找以 nums[i] 开头的最长连续子数组长度
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 提前结束
            if (max >= nums.length - i) {
                break;
            }
            // 结束位置从后往前找
            int end = nums.length - 1;
            int len = end - i + 1;
            while (end > i && len > max) {
                // 判断 nums[i ... end] 是否满足条件
                // 即判断该区间的 0 和 1 的数目是否相同
                // 1. 若该区间的长度为奇数，end 减一，进行下一次循环
                // 2. 该区间的 0 的个数为 record[end+1] - record[i]
                if ((len & 1) == 1) {
                    end--;
                    len = end - i + 1;
                    continue;
                }
                int zn = record[end+1] - record[i]; // 0 的个数
                int on = len - zn;  // 1 的个数
                if (zn == on) {
                    max = len;
                    break;
                }
                // 若两者不等，比较两者之差 a，end 前移 a/2
                int a = Math.abs(zn - on);
                end -= a / 2;
                len = end - i + 1;
            }
        }

        return max;
    }

    /**
     * 优化：O(n) 时间复杂度解法
     */
    public int findMaxLength_better(int[] nums) {
        // 关键是把所有的 0 转换为 -1，这样就转化为求区间和为 0 的最长连续数组长度
        // 在一次遍历的时候，如果遇到当前 sum 和之前的 sum 相同，说明两者之间的连续数组符号条件
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        // HashMap 的 key 存储 sum，value 为第一次出现该 sum 的索引
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int max = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // 若 sum 为 0，说明当前所有元素的和为 0，更新 max
            if (sum == 0) {
                max = i+1;
            }
            if (hashMap.containsKey(sum)) {
                // 第一次和为 sum 的索引到现在组成的区间的和为 0，和 max 作比较
                max = Math.max(max, i - hashMap.get(sum));
            } else {
                hashMap.put(sum, i);
            }
        }

        return max;
    }
}
