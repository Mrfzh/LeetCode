package array;

public class Question665 {
    public static void main(String[] args) {
        int[] nums = {4,2,1};
        System.out.println(new Question665().checkPossibility(nums));
    }

    /**
     * 给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，
     * 该数组能否变成一个非递减数列。
     *
     * 我们是这样定义一个非递减数列的： 
     * 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。
     *
     * 说明:  n 的范围为 [1, 10,000]。
     *
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int time = 0;   // 递减的次数（即 a[i] < a[i-1]），递减次数在两次以上肯定不行
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                time++;
                if (time == 2) {
                    return false;
                }
                // 判断不能通过改变一个元素来变成非递减的情况
                // 例如 3 4 2 3、7 7 6 6
                if (i != nums.length - 1 && i != 1 &&
                        nums[i-2] > nums[i] && nums[i-1] > nums[i+1]) {
                    return false;
                }
            }
        }

        return true;
    }
}
