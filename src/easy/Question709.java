package easy;

public class Question709 {
    public static void main(String[] args) {
        System.out.println(new Question709().toLowerCase("Hello"));
        System.out.println(new Question709().toLowerCase("here"));
        System.out.println(new Question709().toLowerCase("LOVELY"));
    }

    /**
     * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，
     * 并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
     *
     * @param str
     * @return
     */
    public String toLowerCase(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] = (char) ('a' + (chars[i] - 'A'));
            }
        }

        return String.valueOf(chars);
    }
}
