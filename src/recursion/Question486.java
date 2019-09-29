package recursion;

import java.util.HashMap;

public class Question486 {
    public static void main(String[] args) {
        int[] nums = {1, 5, 233, 7};
        System.out.println(new Question486().PredictTheWinner(nums));
    }

    /**
     * 给定一个表示分数的非负整数数组。
     * 玩家1从数组任意一端拿取一个分数，随后玩家2继续从剩余数组任意一端拿取分数，然后玩家1拿，……。
     * 每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。
     * 直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
     *
     * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
     *
     * 注意:
     * 1. 1 <= 给定的数组长度 <= 20.
     * 2. 数组里所有分数都为非负数且不会大于10000000。
     * 3. 如果最终两个玩家的分数相等，那么玩家1仍为赢家。
     *
     * 递归 + 记忆化搜索
     *
     * @param nums
     * @return
     */
    public boolean PredictTheWinner(int[] nums) {
        if (nums.length <= 2) {
            return true;
        }

        // 借助二进制来进行记忆化搜索，因为给定数组长度不大于 20，所以用一个 int 表示
        // 从 nums[i ... j] 中选一个数所能获得的最高分数，其中 i 和 j 对应的二进制位为 1
        // （二进制位由低到高对应数组索引 0,1,...）
        // HashMap 的 key 为上述的 int，value 为对应的最高分数
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        // sums[a][b] 表示 nums[a ... b] 的和
        int[][] sums = new int[nums.length][nums.length];
        sums[0][0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[0][i] = sums[0][i-1] + nums[i];
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                sums[i][j] = sums[0][j] - sums[0][i-1];
            }
        }

        // 玩家 1 所能获得的最高分数
        int high = highestScore(nums, 0, nums.length-1, hashMap, sums);

        return high >= sums[0][nums.length-1] - high;
    }

    /**
     * 求玩家从 nums[i ... j] 中选一个数所能获得的最高分数
     */
    private int highestScore(int[] nums, int i, int j, HashMap<Integer, Integer> hashMap,
                             int[][] sums) {
        int bit = (1 << i) | (1 << j);
        if (hashMap.containsKey(bit)) {
            return hashMap.get(bit);
        }
        if (j - i == 1) {
            int max = Math.max(nums[i], nums[j]);
            hashMap.put(bit, max);
            return max;
        }
        // 先看选择 nums[i] 所能获得的最高分数
        int iMax = nums[i] + sums[i+1][j] - highestScore(nums, i+1, j, hashMap, sums);
        // 再看选择 nums[j] 所能获得的最高分数
        int jMax = nums[j] + sums[i][j-1] - highestScore(nums, i, j-1, hashMap, sums);

        int max = Math.max(iMax, jMax);
        hashMap.put(bit, max);
        return max;
    }

    /**
     * 大佬们还有一种 dp 的解法
     * dp[a][b] 表示先手取 nums[a ... b] 时，先手比后手多的分数
     */
}
