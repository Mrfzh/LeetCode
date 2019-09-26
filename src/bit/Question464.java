package bit;

import java.util.HashMap;

public class Question464 {
    public static void main(String[] args) {
        System.out.println(new Question464().canIWin(10, 11));
    }

    /**
     * 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，
     * 先使得累计整数和达到 100 的玩家，即为胜者。
     *
     * 如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
     * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
     *
     * 给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和
     * 另一个整数 desiredTotal（累计和），判断先出手的玩家是否能稳赢
     * （假设两位玩家游戏时都表现最佳）？
     *
     * 你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。
     *
     * 递归 + 位运算 + 记忆化搜索
     *
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int total = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (total < desiredTotal) {
            return false;
        } else if (total == desiredTotal) {
            return (maxChoosableInteger & 1) == 1;  // 有奇数个数可选时，先手必赢
        }

        // 由于 maxChoosableInteger 不会大于 20，所以可以借助位运算，利用 int 数表示组合
        // 从低到高表示 1,2，...,n 是否选过，1 为选过，0 为未选过
        // HashMap 的 key 表示组合，value 表示该组合下先手能赢
        HashMap<Integer, Boolean> hashMap = new HashMap<>();

        return canWin(0, desiredTotal, maxChoosableInteger, hashMap);
    }

    /**
     * 离目标还有 target，现在的组合为 bit，先手是否能赢
     */
    private boolean canWin(int bit, int target, int maxChoosableInteger,
                           HashMap<Integer, Boolean> hashMap) {
        if (hashMap.containsKey(bit)) {
            return hashMap.get(bit);
        }

        boolean result = false;
        for (int i = maxChoosableInteger; i > 0; i--) {
            int currBit = 1 << (i - 1);     // 当前数在二进制组合中的位置
            // 当前数未出现在组合中
            if ((bit & currBit) == 0) {
                int nextBit = bit|currBit;
                // 有两种情况可以赢：
                // 1. 当前数大于等于 target 时，先手选择该数直接胜利
                // 2. 当前数小于 target 时，先手选了该数后对手不能赢
                if (i >= target || !canWin(nextBit, target - i,
                        maxChoosableInteger, hashMap)) {
                    result = true;
                    break;
                }
            }
        }

        hashMap.put(bit, result);
        return result;
    }
}
