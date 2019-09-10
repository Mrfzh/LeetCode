package easy;

public class Question409 {
    public static void main(String[] args) {
        System.out.println(new Question409().longestPalindrome("abccccdd"));
    }

    /**
     * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
     *
     * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
     *
     * @param s
     * @return 最长回文串的长度
     */
    public int longestPalindrome(String s) {
        if (s.equals("")) {
            return 0;
        }
        // 分别记录字符串中小写和大写 26 个字母的数量
        int[] low = new int[26];
        int[] cap = new int[26];
        for (char c : s.toCharArray()) {
            if ('a' <= c && c <= 'z') {
                low[c - 'a']++;
            } else {
                cap[c - 'A']++;
            }
        }

        int res = 0;
        boolean hasOdd = false; // 是否含有单个字符
        for (int i = 0; i < 26; i++) {
            if (!hasOdd && ((low[i] & 1) == 1 || (cap[i] & 1) == 1)) {
                hasOdd = true;
                res += 1;
            }
            if (low[i] != 0) {
                res += (low[i] & 1) == 1? low[i]-1 : low[i];
            }
            if (cap[i] != 0) {
                res += (cap[i] & 1) == 1? cap[i]-1 : cap[i];
            }
        }

        return res;
    }
}
