package easy;

public class Question541 {
    public static void main(String[] args) {
        System.out.println(new Question541().reverseStr("abcdefg", 2));
    }

    /**
     * 给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。
     * 如果剩余少于 k 个字符，则将剩余的所有全部反转。
     * 如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        if (s.length() <= 1 || k == 1) {
            return s;
        }

        // 先将字符串转化为字符数组
        char[] chars = s.toCharArray();
        // 每间隔 k 个字符反转一次
        int start = 0;
        while (start < s.length()) {
            int end = start + k - 1;
            if (end >= s.length()) {
                end = s.length()-1;
            }
            reverse(chars, start, end);
            start += 2*k;
        }

        return String.valueOf(chars);
    }

    /**
     * 将 chars[start ... end] 范围内的字符串反转
     */
    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}
