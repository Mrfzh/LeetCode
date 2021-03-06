package array;

import java.util.Arrays;

public class Question88 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,7,0,0,0};
        int[] nums2 = {1,2,6};
        new Question88().merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     *
     * 说明
     * 1. 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 2. 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n < 1) {
            return;
        }
        //从后往前插入
        for (int i = m + n - 1; (m > 0 && n > 0); i--) {
            if (nums1[m-1] > nums2[n-1]) {
                nums1[i] = nums1[m-1];
                m--;
            } else {
                nums1[i] = nums2[n-1];
                n--;
            }
        }
        //将剩余元素放入
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
        }
    }
}
