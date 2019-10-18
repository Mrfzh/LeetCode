package easy;

public class Question557 {
    public static void main(String[] args) {
        System.out.println(new Question557().reverseWords("Let's take LeetCode contest"));
    }

    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     *
     * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        for (int i = 0; i <= chars.length; i++) {
            if (i == chars.length || (i != 0 && chars[i] == ' ')) {
                reverse(chars, start, i-1);
                start = i+1;
            }
        }

        return String.valueOf(chars);
    }

    /**
     * 反转 chars[start, end] 间的字符
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
