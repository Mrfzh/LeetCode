package skill;

import java.util.Arrays;

public class Question189 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        new Question189().rotate_better(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     *
     * 要求使用空间复杂度为 O(1) 的 原地 算法。
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if (nums.length == 0) {
            return;
        }
        k = k % nums.length;

        // 向右移动 k 次
        for (int i = 0; i < k; i++) {
            // 暂存最后一个元素
            int temp = nums[nums.length - 1];
            // 其它元素后移一位
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j-1];
            }
            // 最后一个元素归位
            nums[0] = temp;
        }
    }

    /**
     * 优化：环状替换
     *
     * 时间复杂度：O(n)，只遍历了每个元素一次。
     * 空间复杂度：O(1)，使用了常数个额外空间。
     */
    public void rotate_better(int[] nums, int k) {
        if (nums.length == 0) {
            return;
        }
        k = k % nums.length;

        int count = 0;  // 计算替换的次数
        for (int i = 0; count < nums.length; i++) {
            int preIndex = i;
            int pre = nums[i];
            int temp;
            int nextIndex;
            do {
                // 下一赋值位置的索引
                nextIndex = (preIndex + k) % nums.length;
                // 将赋值位置的旧元素保存起来
                temp = nums[nextIndex];
                // 进行赋值
                nums[nextIndex] = pre;

                // 更新前索引和前索引元素，用于下次赋值
                preIndex = nextIndex;
                pre = temp;

                // 更新替换的数
                count++;
            } while (i != preIndex);
            // 如果下一位置和起始位置重合，说明已饶了一圈，结束循环
            // 如果饶了一圈后，count < nums.length，说明还有元素未替换
            // 从当前起始位置的下一位开始，再次循环
        }
    }
}
