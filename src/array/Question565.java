package array;

public class Question565 {
    public static void main(String[] args) {
        int[] nums = {5,4,0,3,1,6,2};
        System.out.println(new Question565().arrayNesting(nums));
    }

    /**
     * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到并返回最长的集合S的长度，
     * S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
     *
     * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]...
     * 以此类推，不断添加直到S出现重复的元素。
     *
     * @param nums
     * @return
     */
    public int arrayNesting(int[] nums) {
        int max = 0;
        // 记录 nums[i] 是否已遍历过
        boolean[] hasFound = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 该数已经找过，无需再遍历
            if (hasFound[i]) {
                continue;
            }
            // 从 nums[i] 开始找，直到找到 nums[j] == i
            int count = 0;
            for (int j = i;  ; j = nums[j]) {
                count++;
                hasFound[j] = true;
                if (nums[j] == i) {
                    break;
                }
            }
            max = Math.max(max, count);
        }

        return max;
    }
}
