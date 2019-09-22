package hash;

import java.util.*;

public class Question451 {
    public static void main(String[] args) {
        System.out.println(new Question451().frequencySort("cccaaa"));
        System.out.println(new Question451().frequencySort("Aabb"));
    }

    /**
     * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
     *
     * hash
     *
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        if (s.length() <= 1) {
            return s;
        }

        // HashMap 的 key 存储字符，value 存储该字符出现的次数
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (hashMap.containsKey(c)) {
                hashMap.put(c, hashMap.get(c)+1);
            } else {
                hashMap.put(c, 1);
            }
        }
        // TreeSet 存储字符，按照其出现次数由大到小排列
        TreeSet<Character> treeSet = new TreeSet<>((o1, o2) -> {
            if (hashMap.get(o2) > hashMap.get(o1)) {
                return 1;
            } else {
                return -1;
            }
        });
        treeSet.addAll(hashMap.keySet());

        // 依次打印字符
        StringBuilder builder = new StringBuilder();
        for (char c : treeSet) {
            int num = hashMap.get(c);
            for (int i = 0; i < num; i++) {
                builder.append(c);
            }
        }

        return builder.toString();
    }
}
