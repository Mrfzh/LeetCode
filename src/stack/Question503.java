package stack;

import java.util.Arrays;
import java.util.Stack;

public class Question503 {
    public static void main(String[] args) {
        int[] nums = {1,2,1};
        System.out.println(Arrays.toString(new Question503().nextGreaterElements(nums)));
    }

    /**
     * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
     * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，
     * 这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
     *
     * 栈
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        // 存储数组索引（知道索引自然就知道相应元素了，等于同时存了索引和元素）
        Stack<Integer> indexs = new Stack<>();
        // 存储结果
        int[] res = new int[nums.length];

        // 第一次遍历
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 栈中元素遇到下一个比它大的元素（num）就出栈
            while (!indexs.isEmpty() && nums[indexs.peek()] < num) {
                res[indexs.pop()] = num;
            }
            // 当前元素索引入栈
            indexs.push(i);
        }

        // 第二次遍历
        for (int num : nums) {
            // 栈中元素只剩下一个时退出循环，此时该元素为最大值，不可能有比它大的元素
            if (indexs.size() <= 1) {
                break;
            }
            // 栈中元素遇到下一个比它大的元素（num）就出栈
            while (!indexs.isEmpty() && nums[indexs.peek()] < num) {
                res[indexs.pop()] = num;
            }
        }

        // 最后栈中剩下的元素都是没有最大值的
        while (!indexs.isEmpty()) {
            res[indexs.pop()] = -1;
        }

        return res;
    }
}
