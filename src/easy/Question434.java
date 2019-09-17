package easy;

public class Question434 {
    public static void main(String[] args) {
        System.out.println(new Question434().countSegments("Hello, my name is John"));
    }

    /**
     * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
     *
     * 请注意，你可以假定字符串里不包括任何不可打印的字符。
     *
     * @param s
     * @return
     */
    public int countSegments(String s) {
        s = s.trim();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            // 当前字符不为空格且下一个字符为空格，即增加一个单词
            if (curr != ' ' && ((i == s.length()-1) || (s.charAt(i+1) == ' '))) {
                count++;
            }
        }

        return count;
    }
}
