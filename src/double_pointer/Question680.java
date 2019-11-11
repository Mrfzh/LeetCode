package double_pointer;

public class Question680 {
    public static void main(String[] args) {
        System.out.println(new Question680().validPalindrome("aba"));
        System.out.println(new Question680().validPalindrome("abca"));
    }

    /**
     * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
     *
     * 注意: 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
     *
     * 双指针
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        if (s.length() <= 2) {
            return true;
        }
        char[] chars = s.toCharArray();
        // 双指针
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            if (chars[start] != chars[end]) {
                // 删除 start 和 end 的其中一个，判断剩下的是否为回文
                if (isValid(chars, start+1, end)) {
                    return true;
                }
                if (isValid(chars, start, end-1)) {
                    return true;
                }
                // 都不行的话就返回 false
                return false;
            }
            start++;
            end--;
        }
        // 字符串本身是回文，返回 true
        return true;
    }

    /**
     * 判断 chars[start, end] 是否为回文串
     */
    private boolean isValid(char[] chars, int start, int end) {
        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}
