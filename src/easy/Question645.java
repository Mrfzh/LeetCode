package easy;

import java.util.Arrays;

public class Question645 {
    public static void main(String[] args) {
        int[] nums = {1,2,2,4};
        System.out.println(Arrays.toString(new Question645().findErrorNums(nums)));
    }

    /**
     * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，
     * 导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，
     * 导致集合丢失了一个整数并且有一个元素重复。
     *
     * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
     * 你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
     *
     * 注意:
     * 给定数组的长度范围是 [2, 10000]。
     * 给定的数组是无序的。
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        // record[i] 存储 i 的个数
        int[] record = new int[nums.length + 1];
        // 存储结果
        int[] res = new int[2];
        // 遍历 nums 数组，找到重复的数
        for (int num : nums) {
            if (record[num] == 1) {
                res[0] = num;
                continue;
            }
            record[num]++;
        }
        // 遍历 record 数组，找到缺少的数
        for (int i = 1; i < record.length; i++) {
            if (record[i] == 0) {
                res[1] = i;
                break;
            }
        }

        return res;
    }
}
