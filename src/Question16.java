import java.util.Arrays;

public class Question16 {
    public static void main(String[] args) {
        int [] nums = {-1, 2, 1, -4};
        System.out.println(threeSumClosest(nums, 1));
    }

    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
     * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     *
     * @param nums 给定的数组
     * @param target 目标值
     * @return 最接近target的三个数的和
     */
    private static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);  //先排序
        int closest = nums[0] + nums[1] + nums[2];  //存储最接近的数
        //固定其中一个数，使用双指针遍历找出其他两个数
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(sum - target) < Math.abs(closest - target)) {  //判断是否要更新最接近的数
                    closest = sum;
                }
                if (sum > target) {
                    r--;
                } else if (sum < target) {
                    l++;
                } else {    //此时sum == target，最接近的数就是target本身
                    return target;
                }
            }
        }

        return closest;
    }
}
