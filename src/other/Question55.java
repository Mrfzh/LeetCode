package other;

public class Question55 {
    public static void main(String[] args) {
        int [] nums = {3,2,1,2,4,1,0,2,4};
        System.out.println(canJump2(nums));
    }

    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个位置。
     *
     * 从前往后递归，不过超出时间限制
     *
     * @param nums
     * @return
     */
    private static boolean canJump(int[] nums) {
        if (nums.length < 1) {
            return true;
        }
        return canJumpInRange(nums, 0);
    }

    private static boolean canJumpInRange(int [] nums, int start) {
        int n = nums.length - start;
        if (n <= 1) {
            return true;
        }

        int curr = nums[start];
        if (curr == 0) {
            return false;
        }
        for (int i = curr; i >= 1; i--) {
            if (i >= n - 1) {
                return true;
            }
            if (canJumpInRange(nums, start+i)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 从后往前遍历
     */
    private static boolean canJump2(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        int n = 1;  //到达目标点所需的步数
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= n) { //在该位置可达，重置n
                n = 1;
            } else {    //在该位置不可达，n要加1
                n++;
            }
        }

        return n == 1;
    }
}
