package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Question350 {
    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        System.out.println(Arrays.toString(
                new Question350().intersect(nums1, nums2)));
    }

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     * 说明：
     * 1. 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     * 2. 我们可以不考虑输出结果的顺序。
     *
     * hash
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        // 记录 nums1 元素，key 为元素，value 为该元素出现次数
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num1 : nums1) {
            if (hashMap.containsKey(num1)) {
                hashMap.replace(num1, hashMap.get(num1)+1);
            } else {
                hashMap.put(num1, 1);
            }
        }

        List<Integer> resList = new ArrayList<>();
        for (int num2 : nums2) {
            if (hashMap.containsKey(num2)) {
                resList.add(num2);
                if (hashMap.get(num2) == 1) {
                    hashMap.remove(num2);
                } else {
                    hashMap.replace(num2, hashMap.get(num2)-1);
                }
            }
        }

        int[] res = new int[resList.size()];
        int i = 0;
        for (int num : resList) {
            res[i++] = num;
        }

        return res;
    }
}
