package easy;

public class Question414 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1};
        System.out.println(new Question414().thirdMax(nums));
    }

    /**
     * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。
     * 要求算法时间复杂度必须是O(n)。
     *
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int first = nums[0];  // 第一大
        int second = 0; // 第二大
        int third = 0;  // 第三大
        boolean hasSecond = false;  // 是否有第二大
        boolean hasThird = false;   // 是否有第三大
        int i;
        for (i = 1; i < nums.length; i++) {
            int curr = nums[i];
            if (curr > first) {
                if (!hasSecond) {
                    second = first;
                    first = curr;
                    hasSecond = true;
                } else {
                    third = second;
                    second = first;
                    first = curr;
                    hasThird = true;
                    break;
                }
            } else if (curr < first){
                if (!hasSecond) {
                    second = curr;
                    hasSecond = true;
                } else {
                    if (curr > second) {
                        third = second;
                        second = curr;
                        hasThird = true;
                        break;
                    } else if (curr < second) {
                        third = curr;
                        hasThird = true;
                        break;
                    }
                }
            }
        }

        // 如果遍历完还没有找到第三大，就返回第一大
        if (!hasThird) {
            return first;
        }

        // 继续遍历下面的
        for (int j = i+1; j < nums.length; j++) {
            int curr = nums[j];
            if (curr > first) {
                third = second;
                second = first;
                first = curr;
            } else if (curr < first) {
                if (curr > second) {
                    third = second;
                    second = curr;
                } else if (curr < second && curr > third) {
                    third = curr;
                }
            }
        }

        return third;
    }

    /**
     * 简化
     */
    public int thirdMax_simplify(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        long first = Long.MIN_VALUE;  // 第一大
        long second = Long.MIN_VALUE; // 第二大
        long third = Long.MIN_VALUE;  // 第三大
        for (int num : nums) {
            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (num < first && num > second) {
                third = second;
                second = num;
            } else if (num < second && num > third) {
                third = num;
            }
        }

        return (int) (third == Long.MIN_VALUE ? first : third);
    }
}
