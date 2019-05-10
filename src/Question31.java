import java.util.Arrays;

public class Question31 {
    public static void main(String[] args) {
        int [] nums = {1,1,5};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     *实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     *
     * 必须原地修改，只允许使用额外常数空间。
     *
     * @param nums
     */
    private static void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }

        int n = nums.length;
        boolean isFound = false;
        for (int i = n-1; i > 0; i--) {
            if (nums[i-1] < nums[i]) {
                //找出nums[i ... n-1]中刚大于a[i-1]的数
                int key = nums[i-1];
                int k = -1;  //nums[k]为刚大于a[i-1]的数
                for (int j = n-1; j >= i; j--) {
                    if (nums[j] > key) {
                        k = j;
                        break;
                    }
                }
                //交换nums[i-1]和nums[k]
                int temp = nums[i-1];
                nums[i-1] = nums[k];
                nums[k] = temp;
                //nums[i ... n-1]按升序排列
                Arrays.sort(nums, i, n);

                isFound = true;
                break;
            }
        }

        //如果已经是最大
        if (!isFound) {
            Arrays.sort(nums);
        }
    }
}
