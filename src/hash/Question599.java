package hash;

import java.util.*;

public class Question599 {
    public static void main(String[] args) {
        String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = {"KFC", "Shogun", "Burger King"};
        System.out.println(Arrays.toString(new Question599().findRestaurant(list1, list2)));
    }

    /**
     * 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，
     * 每个餐厅的名字用字符串表示。
     *
     * 你需要帮助他们用找出他们共同喜爱的餐厅，该餐厅具有最少的索引和。
     * 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。
     *
     * 提示:
     * 两个列表的长度范围都在 [1, 1000]内。
     * 两个列表中的字符串的长度将在[1，30]的范围内。
     * 下标从0开始，到列表的长度减1。
     * 两个列表都没有重复的元素。
     *
     * hash
     *
     * @param list1
     * @param list2
     * @return
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        // 将 list2 的值和索引记录在 HashMap 中
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < list2.length; i++) {
            hashMap.put(list2[i], i);
        }
        List<String> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;    // 最小索引和
        // 遍历 list1 的元素，找到和 list2 的值相同且索引和最小的元素
        for (int i = 0; i < list1.length && i <= min; i++) {
            if (hashMap.containsKey(list1[i])) {
                int sum = i + hashMap.get(list1[i]);
                if (sum == min) {
                    res.add(list1[i]);
                } else if (sum < min) {
                    res.clear();
                    res.add(list1[i]);
                }
                min = Math.min(min, sum);
            }
        }

        return res.toArray(new String[res.size()]);
    }
}
