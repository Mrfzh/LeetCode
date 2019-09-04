package easy;

public class Question392 {
    public static void main(String[] args) {
        System.out.println(new Question392().isSubsequence("abc", "ahbgdc"));
        System.out.println(new Question392().isSubsequence("axc", "ahbgdc"));
    }

    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），
     * 而 s 是个短字符串（长度 <=100）。
     *
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变
     * 剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if (s.equals("")) {
            return true;
        }

        int sc = 0;
        char curr = s.charAt(sc);

        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == curr) {
                if (sc == s.length()-1) {
                    return true;
                } else {
                    sc++;
                    curr = s.charAt(sc);
                }
            }
        }

        return false;
    }
}
