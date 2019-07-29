package hash;

import java.util.HashMap;
import java.util.HashSet;

public class Question205 {
    public static void main(String[] args) {
        System.out.println(new Question205().isIsomorphic("egg", "add"));
        System.out.println(new Question205().isIsomorphic("foo", "bar"));
        System.out.println(new Question205().isIsomorphic("paper", "title"));
        System.out.println(new Question205().isIsomorphic("ab", "aa"));
    }

    /**
     * 给定两个字符串 s 和 t，判断它们是否是同构的。
     * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
     *
     * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。
     * 两个字符不能映射到同一个字符上，但字符可以映射自己本身。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> hashMap = new HashMap<>();
        // key 为 s 中的字符，value 为 t 中对应的映射字符
        HashSet<Character> hashSet = new HashSet<>();
        // 存储 value 中的元素

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (!hashMap.containsKey(curr)) {
                if (!hashSet.contains(t.charAt(i))) {
                    hashMap.put(curr, t.charAt(i));
                    hashSet.add(t.charAt(i));
                } else {
                    // 若该 value 已经有其他映射，返回 false
                    return false;
                }
            } else {
                if (t.charAt(i) != hashMap.get(curr)) {
                    return false;
                }
            }
        }

        return true;
    }
}
