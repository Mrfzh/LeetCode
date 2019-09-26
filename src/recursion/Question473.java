package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question473 {
    public static void main(String[] args) {
        int[] nums = {2,2,2,2,2,6};
        System.out.println(new Question473().makesquare(nums));
    }

    /**
     * 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，
     * 请找出一种能使用所有火柴拼成一个正方形的方法。
     * 不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
     *
     * 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
     *
     * 注意:
     * 1. 给定的火柴长度和在 0 到 10^9之间。
     * 2. 火柴数组的长度不超过15。
     *
     * 借鉴 464 题的思路，递归 + 记忆化搜索（利用二进制）
     *
     * @param nums
     * @return
     */
    public boolean makesquare(int[] nums) {
        if (nums.length < 4) {
            return false;
        }

        // 计算所有火柴的总长度，注意溢出问题
        float sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 如果不能被 4 整除，不可能组成正方形
        if (sum % 4 != 0) {
            return false;
        }
        // 每一边的长度
        float sideLen = sum / 4f;
        // 先对数组排序
        Arrays.sort(nums);

        return canMake(0, 4, sideLen, nums);
    }

    /**
     * bit 中二进制位（由低到高依次对应数组索引 0,1,2,...）为 1 说明该位置的火柴已使用
     * 判断剩下的火柴能否组成 target 条边，每条边的长度为 len
     */
    private boolean canMake(int bit, int target, float len, int[] nums) {
        if (target == 1) {
            return true;
        }

        // 先找出第一个未使用的火柴
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            int currBit = 1 << i;
            if ((currBit & bit) == 0) {
                start = i;
                break;
            }
        }
        if (nums[start] > len) {
            return false;
        } else if (nums[start] == len) {
            return canMake(bit|(1 << start), target-1, len, nums);
        } else {
            // 此时 nums[start] < len
            // 找出可以和 nums[start] 组合成 len 长度的火柴集合
            List<Integer> nextBitList = new ArrayList<>();
            find(bit, start, len, nums, nextBitList);
            for (int nextBit : nextBitList) {
                if (canMake(nextBit, target - 1, len, nums)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 从 start 开始，从未使用的火柴中，找到和为 target 的组合
     * nums 数组为升序，bit 存储当前的组合，若找到组合，则存入 res 中
     */
    private void find(int bit, int start, float target, int[] nums, List<Integer> res) {
        int nextBit = bit | (1 << start);
        if (nums[start] == target) {
            res.add(nextBit);
        } else if (nums[start] < target){
            for (int i = start+1; i < nums.length; i++) {
                // 注意已使用的火柴不能再用
                if ((bit & (1 << i)) == 0) {
                    find(nextBit, i, target - nums[start], nums, res);
                }
            }
        }
    }
}
