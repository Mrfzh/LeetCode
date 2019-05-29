package other;

public class Question58 {
    public static void main(String[] args) {
        String s = "hello world";
        System.out.println(lengthOfLastWord(s));
    }

    /**
     * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。如果不存在最后一个单词，请返回 0 。
     *
     * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
     *
     * @param s
     * @return
     */
    private static int lengthOfLastWord(String s) {
        String str = s.trim();
        if (str.equals("")) {
            return 0;
        }

        int result = 0;
        for (int i = str.length()-1; i >= 0; i--) {
            if (str.charAt(i) != ' ') {
                result++;
            } else {
                break;
            }
        }

        return result;
    }
}
