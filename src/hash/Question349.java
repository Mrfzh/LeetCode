package hash;

import java.util.Arrays;
import java.util.HashSet;

public class Question349 {
    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        System.out.println(Arrays.toString(
                new Question349().intersection(nums1, nums2)));
    }

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     * 说明:
     * 1. 输出结果中的每个元素一定是唯一的。
     * 2. 我们可以不考虑输出结果的顺序。
     *
     * hash
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num1 : nums1) {
            hashSet.add(num1);
        }

        HashSet<Integer> resSet = new HashSet<>();
        for (int num2 : nums2) {
            if (hashSet.contains(num2)) {
                resSet.add(num2);
            }
        }

        int[] res = new int[resSet.size()];
        int i = 0;
        for (int num : resSet) {
            res[i++] = num;
        }

        return res;
    }
}
