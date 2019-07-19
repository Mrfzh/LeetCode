package recursion;

public class Question152 {
    public static void main(String[] args) {
        int[] nums = {2,3,-2,4,-1,2,3,-2,4,2,3,-2,4,5,-1,2,3,-2,4};
        System.out.println(new Question152().maxProduct(nums));
    }

    /**
     * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
     *
     * 递归
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        return find(nums, 0, nums.length);
    }

    /**
     * 找出nums[start, end)中的乘积最大的连续子序列
     */
    private int find(int[] nums, int start, int end) {
        int n = end - start;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return nums[start];
        }

        //遍历，查看是否有0
        int zeroIndex = -1;
        boolean hasZeor = false;
        int product = 1;
        for (int i = start; i < end; i++) {
            if (nums[i] == 0) {
                zeroIndex = i;
                hasZeor = true;
                break;
            }
            product *= nums[i];
        }

        //如果有0，递归左0左右两边的最大值，再比较
        if (hasZeor) {
            int left = find(nums, start, zeroIndex);
            int right = find(nums, zeroIndex+1, end);
            if (left < 0 && right < 0) {
                return 0;
            } else {
                return left > right? left : right;
            }
        }
        //如果没有0的话，再慢慢找最大值
        else {
            //整个乘积最大
            if (product > 0) {
                return product;
            }
            //左右两边去除负数
            else {
                //先去左边
                int left = product;
                for (int i = start; i < end; i++) {
                    left /= nums[i];
                    if (left > 0) {
                        break;
                    }
                }
                //再去右边
                int right = product;
                for (int i = end-1; i >= start; i--) {
                    right /= nums[i];
                    if (right > 0) {
                        break;
                    }
                }
                //最后比较
                return left > right? left : right;
            }
        }
    }

}
