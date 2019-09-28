package skill;

public class Question477 {
    public static void main(String[] args) {
        int[] nums = {4, 14, 2};
        System.out.println(new Question477().totalHammingDistance(nums));
    }

    /**
     * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
     *
     * 计算一个数组中，任意两个数之间汉明距离的总和。
     *
     * @param nums
     * @return
     */
    public int totalHammingDistance(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        // 转换思路：先求得每个二进制位的 0 和 1 的数量
        // 最终结果就是把每个二进制位的 0 和 1 的数量相乘的结果相加起来

        // 遍历数组，求每个位置的 0 和 1 数量
        int[] counts = new int[32];     // 存储每个位置的 1 的数量
        for (int num : nums) {
            for (int i = 0; num != 0 && i < 32; i++) {
                int temp = num & 1;
                if (temp == 1) {
                    counts[i]++;
                }
                num = num >> 1;
            }
        }

        int res = 0;
        for (int count : counts) {
            res += count * (nums.length - count);
        }

        return res;
    }
}
