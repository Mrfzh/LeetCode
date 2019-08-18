package hash;

import java.util.HashMap;
import java.util.HashSet;

public class Question290 {
    public static void main(String[] args) {
        System.out.println(new Question290().wordPattern(
                "abba", "dog cat cat dog"));
        System.out.println(new Question290().wordPattern(
                "abba", "dog cat cat fish"));
        System.out.println(new Question290().wordPattern(
                "aaaa", "dog cat cat dog"));
        System.out.println(new Question290().wordPattern(
                "abba", "dog dog dog dog"));
    }

    /**
     * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
     *
     * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 
     * 中的每个非空单词之间存在着双向连接的对应规律。
     *
     * 说明:
     * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
     *
     * hash 法
     *
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        str = str.trim();
        if (pattern.equals("")) {
            return false;
        }
        String[] strs = str.split(" ");
        if (!(strs.length == pattern.length())) {
            return false;
        }

        HashMap<Character, String> hashMap = new HashMap<>();
        HashSet<String> hashSet = new HashSet<>();  // 存储已加入 HashMap 的单词
        for (int i = 0; i < pattern.length(); i++) {
            char curr = pattern.charAt(i);
            if (!hashMap.containsKey(curr)) {
                if (hashSet.contains(strs[i])) {
                    return false;
                } else {
                    hashSet.add(strs[i]);
                    hashMap.put(curr, strs[i]);
                }
            } else {
                if (!hashMap.get(curr).equals(strs[i])) {
                    return false;
                }
            }
        }

        return true;
    }
}
