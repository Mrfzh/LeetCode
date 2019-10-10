package double_pointer;

import java.util.*;

public class Question524 {
    public static void main(String[] args) {
        List<String> d = Arrays.asList("a","b","c");
        System.out.println(new Question524().findLongestWord("abpcplea", d));
    }

    /**
     * 给定一个字符串和一个字符串字典，
     * 找到字典里面可以通过删除给定字符串的某些字符来得到的最长的字符串，
     * 如果答案不止一个，返回字典顺序（即 a 开头的小于 b 开头的）最小的字符串。如果答案不存在，则返回空字符串。
     *
     * 说明:
     * 所有输入的字符串只包含小写字母。
     * 字典的大小不会超过 1000。
     * 所有输入的字符串长度不会超过 1000。
     *
     * 排序 + 双指针（判断子序列）
     *
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord(String s, List<String> d) {
        // 对字典的字符串进行排序，按照字符串长度由大到小的顺序排列
        // 如果长度相同，在字典中先出现的排前面
        d.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() == o2.length() ? o1.compareTo(o2)
                        : o2.length() - o1.length();
            }
        });

        // 判断是否有满足条件的字符串
        for (String str : d) {
            if (isValid(s, str)) {
                return str;
            }
        }

        return "";
    }

    /**
     * 判断 s2 是否可以通过删除 s1 的某些字符来得到
     * （其实就是判断 s2 是否为 s1 的子序列，可用双指针解决）
     */
    private boolean isValid(String s1, String s2) {
        if (s2.length() > s1.length()) {
            return false;
        }
        if (s2.equals("") || s2.equals(s1)) {
            return true;
        }

        int p2 = 0;     // 指向 s2
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(p2)) {
                p2++;
            }
            if (p2 == s2.length()) {
                return true;
            }
        }

        return false;
    }
}
