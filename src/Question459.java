public class Question459 {
    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("abab"));
        System.out.println(repeatedSubstringPattern("ab"));
        System.out.println(repeatedSubstringPattern("aba"));
        System.out.println(repeatedSubstringPattern("abcabcabcabc"));
        System.out.println(repeatedSubstringPattern("a"));
    }

    /**
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母。
     *
     * @param s
     * @return
     */
    private static boolean repeatedSubstringPattern(String s) {
        if (s.length() == 1) {
            return false;
        }

        char first = s.charAt(0);     //字符首字母
        String repeat;
        for (int i = 1; i <= s.length()/2; i++) {  //找出重复字符（如果有的话）
            if (s.charAt(i) == first) {
                repeat = s.substring(0, i);
                if(isValid(s, repeat)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * s字符串是否由repeat字符串重复构成
     */
    private static boolean isValid(String s, String repeat) {
        int rl = repeat.length();
        int sl = s.length();
        if (sl % rl != 0) {
            return false;
        }
        for (int i = 0; i < s.length(); i += rl) {
            if (!s.substring(i, i+rl).equals(repeat)) {
                return false;
            }
        }
        return true;
    }
}
