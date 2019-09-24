package stack;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Question456 {
    public static void main(String[] args) {
        int[] nums = {-1, 3, 2, 0};
        System.out.println(new Question456().find132pattern_better(nums));
    }

    /**
     * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：
     * 当 i < j < k 时，ai < ak < aj。
     * 设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
     *
     * 注意：n 的值小于15000。
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        // 记录一些 13 区间，Pair 的 key 为 1，value 为 3
        // List 的排列顺序，key 和 value 逐渐减少，当下一个的 value 大于上一个时，上一个无用，移除掉
        List<Pair<Integer, Integer>> records = new ArrayList<>();

        boolean hasCurrMin = false; // 记录当前的 1 是否找到 3 匹配
        int currMin = 0;    // 当前的 1
        for (int num : nums) {
            if (records.isEmpty()) {
                if (hasCurrMin) {
                    if (num > currMin + 1) {
                        records.add(new Pair<>(currMin, num));
                        hasCurrMin = false;
                    } else if (currMin > num) {
                        currMin = num;
                    }
                } else {
                    currMin = num;
                    hasCurrMin = true;
                }
            } else {
                // 从前往后遍历，如果发现 num >= value，就说明找不到了
                for (Pair<Integer, Integer> record : records) {
                    if (num >= record.getValue()) {
                        break;
                    }
                    // 执行到这里，说明 num < value，再看下 num 是否大于 key
                    if (num > record.getKey()) {
                        return true;
                    }
                }
                // 执行到这里，说明 num 不是 2，看下 num 是否能加入到 record 中
                boolean hasAdd2Recrod = false;
                if (hasCurrMin) {
                    if (num > currMin + 1) {
                        records.add(new Pair<>(currMin, num));
                        hasAdd2Recrod = true;
                        hasCurrMin = false;
                    } else if (num < currMin) {
                        currMin = num;
                    }
                } else {
                    int key = records.get(records.size()-1).getKey();
                    int value = records.get(records.size()-1).getValue();
                    // 如果 num 小于最后一个 record 的 key，其成为 currMin
                    if (num < key) {
                        hasCurrMin = true;
                        currMin = num;
                    }
                    // 如果 num 大于最后一个 record 的 value，更新最后一个 record
                    if (num > value) {
                        records.remove(records.size()-1);
                        records.add(new Pair<>(key, num));
                        hasAdd2Recrod = true;
                    }
                }
                // 如果当前 num 添加到了 records 中，说明最后一个 record 有更新
                // 如果前面的 record 的 value 小于或等于 最后一个 record 的 value，移除它
                if (hasAdd2Recrod) {
                    int lastRecordValue = records.get(records.size()-1).getValue();
                    for (int i = records.size()-2; i >= 0; i--) {
                        int currValue = records.get(i).getValue();
                        if (currValue <= lastRecordValue) {
                            records.remove(i);
                        }
                    }
                }
            }
        }

        return false;

    }

    /**
     * 优化：利用栈
     */
    public boolean find132pattern_better(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        // 思路：从后往前遍历，先找 132 中的 32，用一个变量记录 2 的值，2 要尽可能大
        int second = Integer.MIN_VALUE;     // 2
        Stack<Integer> stack = new Stack<>();   // 栈存储的是可能成为 2 的值
        for (int i = nums.length-1; i >= 0; i--) {
            int num = nums[i];
            if (num < second) {
                return true;
            }
            while (!stack.isEmpty() && stack.peek() < num) {
                // 若当前 num 比栈顶元素大，说明当前 num 可以为 3
                // 栈顶元素出栈，如果它比原来的 2 大，它成为新的 2
                second = Math.max(second, stack.pop());
            }
            // 当前 num 入栈，因为它也可能成为下一个 2
            stack.push(num);
        }

        return false;
    }
}
