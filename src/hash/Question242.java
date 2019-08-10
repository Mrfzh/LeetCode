package hash;

import java.util.HashMap;

public class Question242 {
    public static void main(String[] args) {
        System.out.println(new Question242().isAnagram("anagram", "nagaram"));
        System.out.println(new Question242().isAnagram("rat", "car"));
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * （也就是判断两个字母含有的字母是否相同）
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> hashMap = new HashMap<>();
        // 记录字符串 s 中各字符数量
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (hashMap.containsKey(curr)) {
                hashMap.put(curr, hashMap.get(curr)+1);
            } else {
                hashMap.put(curr, 1);
            }
        }
        // 遍历字符串 t，比对字符串
        for (int i = 0; i < t.length(); i++) {
            char curr = t.charAt(i);
            if (!hashMap.containsKey(curr)) {
                return false;
            } else {
                int num = hashMap.get(curr);
                if (num == 1) {
                    hashMap.remove(curr);
                } else {
                    hashMap.put(curr, num-1);
                }
            }
        }

        return hashMap.isEmpty();
    }

    /**
     * 优化：由于限定为小写字母，所以可以不用 HashMap，而是用一个长度为
     * 26 的数组来记录字母出现的次数。
     */
}
