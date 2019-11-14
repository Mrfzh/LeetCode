package hash;

import java.util.*;

public class Question692 {
    public static void main(String[] args) {
        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        System.out.println(new Question692().topKFrequent(words, 4));
    }

    /**
     * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
     *
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
     *
     * 注意：
     * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
     * 输入的单词均由小写字母组成。
     *
     * hash + 排序
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        // 利用 HashMap 存储每个单词出现的次数
        HashMap<String, Integer> hashMap = new HashMap<>();
        // List 存储出现的单词
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if (hashMap.containsKey(word)) {
                hashMap.put(word, hashMap.get(word) + 1);
            } else {
                list.add(word);
                hashMap.put(word, 1);
            }
        }

        // 对 List 排序，按照出现次数由多到少顺序排列，若次数相同，则按字母顺序排列
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int time1 = hashMap.get(o1);
                int time2 = hashMap.get(o2);
                return time1 == time2? o1.compareTo(o2) : time2 - time1;
            }
        });

        // 返回 List 的前 k 个单词
        return list.subList(0, k);
    }
}
