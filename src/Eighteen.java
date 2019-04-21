import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Eighteen {
    public static void main(String[] args) {
        int [] nums = {1,0,-1,0,-2,2};
        System.out.println(fourSum(nums, 0));
    }

    /**
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
     * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     *
     * @param nums 给定的数组
     * @param target 目标值
     * @return
     */
    private static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        if (n < 4) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);  //先排序
        HashSet<List<Integer>> result = new HashSet<>();
        //先固定两个数（较小的两个）：nums[i]和nums[j]，再利用双指针法求出另外两个数
        for (int i = 0; i < n-3; i++) {
            for (int j = i+1; j < n-2; j++) {
                int sum = nums[i] + nums[j];
                if (target - sum < 2 * nums[j]) {   //此时加上后面的数肯定大于target
                    break;
                }
                int l = j + 1;
                int r = n - 1;
                while (l < r) {
                    int sum2 = sum + nums[l] + nums[r];
                    if (sum2 > target) {
                        r--;
                    } else if (sum2 < target) {
                        l++;
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        result.add(list);
                        l++;
                        r--;
                    }
                }
            }
        }

        return new ArrayList<>(result);
    }
}
