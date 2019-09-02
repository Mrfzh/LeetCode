package bit;

public class Question389 {
    public static void main(String[] args) {
        System.out.println(new Question389().findTheDifference_bit("abcd", "abcde"));
    }

    /**
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     *
     * 请找出在 t 中被添加的字母。
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        // 按顺序存储字符串 s 中的字母个数
        int[] bucket = new int[26];
        for (int i = 0; i < s.length(); i++) {
            bucket[s.charAt(i) - 'a']++;
        }

        // 遍历字符串 t 的字母，每找到一个，对应 bucket 的字母个数减一。
        for (int i = 0; i < t.length(); i++) {
            char curr = t.charAt(i);
            int index = curr - 'a';
            if (bucket[index] == 0) {
                return curr;
            } else {
                bucket[index]--;
            }
        }

        return 'a';
    }

    /**
     * 位运算
     */
    public char findTheDifference_bit(String s, String t) {
        int res = 0;
        for (char cs : s.toCharArray()) {
            res ^= (int) cs;
        }
        for (char ct : t.toCharArray()) {
            res ^= (int) ct;
        }

        return (char)res;
    }
}
