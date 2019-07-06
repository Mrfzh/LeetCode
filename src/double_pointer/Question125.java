package double_pointer;

public class Question125 {
    public static void main(String[] args) {
        String s = ".,";
        System.out.println(new Question125().isPalindrome(s));
    }

    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 双指针
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s.equals("")) {
            return true;
        }

        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
                if (i == s.length()) {
                    return true;
                }
            }
            while (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
                if (j == -1) {
                    return true;
                }
            }
            if (Character.toLowerCase(s.charAt(i)) !=
                    Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }
}
