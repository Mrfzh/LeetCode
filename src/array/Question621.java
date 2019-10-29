package array;

import java.util.*;

public class Question621 {
    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        System.out.println(new Question621().leastInterval(tasks, 2));
    }

    /**
     * 给定一个用字符数组表示的 CPU 需要执行的任务列表。
     * 其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
     * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
     * CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
     *
     * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，
     * 因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     *
     * 你需要计算完成所有任务所需要的最短时间。
     *
     * 注：
     * 任务的总个数为 [1, 10000]。
     * n 的取值范围为 [0, 100]。
     *
     * 数组 + 排序
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        // 先统计各字母的数量
        int[] nums = new int[26];
        for (char task : tasks) {
            nums[task - 'A']++;
        }
        // 对各字母的数量进行排序
        Arrays.sort(nums);
        // 统计元素不为 0 的个数
        int notZeroNum = 0;
        for (int num : nums) {
            if (num != 0) {
                notZeroNum++;
            }
        }
        // 每次将前 n+1 个元素（由大到小遍历）减一
        int time = 0;
        while (true) {
            int count = 0;  // 记录这次循环减一的元素个数
            int temp = n;
            for (int i = nums.length - 1; i >= 0 && temp >= 0; i--, temp--) {
                // 提前结束内层循环
                if (nums[i] == 0) {
                    break;
                }
                nums[i]--;
                count++;
                // 更新 notZeroNum
                if (nums[i] == 0) {
                    notZeroNum--;
                }
            }
            // 当所有元素都为 0，说明任务执行完毕，退出外层循环
            if (notZeroNum == 0) {
                time += count;
                break;
            } else {
                time += n+1;
            }
            // 每次都要重新排序
            Arrays.sort(nums);
        }

        return time;
    }

}
