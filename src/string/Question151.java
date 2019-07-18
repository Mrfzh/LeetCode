package string;

public class Question151 {
    public static void main(String[] args) {
        System.out.println("---" + new Question151().reverseWords("the sky is blue") + "---");
        System.out.println("---" + new Question151().reverseWords("  hello world!  ") + "---");
        System.out.println("---" + new Question151().reverseWords("a good   example") + "---");
    }

    /**
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     *
     * 说明：
     * 1. 无空格字符构成一个单词。
     * 2. 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 3. 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        s = s.trim();
        if (s.equals("")) {
            return s;
        }

        int end = s.length()-1; //单词结束位置
        boolean isSpace = false;   //后一个字符是否为空格
        StringBuilder builder = new StringBuilder();
        for (int i = s.length()-1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                //遇到空格后，判断后一个字符是否为空格
                if (!isSpace) {
                    //不是空格说明找到一个单词
                    builder.append(s, i+1, end+1);
                    builder.append(" ");
                }
                isSpace = true;
            } else if (isSpace) {
                //当前不是空格，但后一个字符是空格，说明该字符是单词结尾
                end = i;
                isSpace = false;
            }
        }
        builder.append(s, 0, end+1);

        return builder.toString();
    }

    /**
     * 从前往后的话，可以用栈记录
     */
}
