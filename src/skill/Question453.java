package skill;

public class Question453 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(new Question453().minMoves(nums));
    }

    /**
     * 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。
     * 每次移动可以使 n - 1 个元素增加 1。
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        // 易知，每次应使除了最大的数外，其他数加一
        // 这等价于最大的数减一，所以最小移动次数为：(a1 - min) + (a2 - min) + ... + (an - min)
        // a1, a2, ..., an 为数组元素，min 为数组元素的最小值

        // 找到数组元素的最小值
        int min = nums[0];
        for (int num : nums) {
            if (min > num) {
                min = num;
            }
        }
        // 计算结果
        int res = 0;
        for (int num : nums) {
            res += num - min;
        }

        return res;
    }
}
