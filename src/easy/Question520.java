package easy;

public class Question520 {
    public static void main(String[] args) {
        System.out.println(new Question520().detectCapitalUse("leetcode"));
        System.out.println(new Question520().detectCapitalUse("Google"));
        System.out.println(new Question520().detectCapitalUse("FlaG"));
    }

    /**
     * 给定一个单词，你需要判断单词的大写使用是否正确。
     *
     * 我们定义，在以下情况时，单词的大写用法是正确的：
     * 1. 全部字母都是大写，比如"USA"。
     * 2. 单词中所有字母都不是大写，比如"leetcode"。
     * 3. 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
     *
     * 否则，我们定义这个单词没有正确使用大写字母。
     *
     * 注意: 输入是由大写和小写拉丁字母组成的非空单词。
     *
     * @param word
     * @return
     */
    public boolean detectCapitalUse(String word) {
        if (word.length() == 1) {
            return true;
        }

        // 判断第一个字符是大写还是小写
        char first = word.charAt(0);
        if ('a' <= first && first <= 'z') {
            return judge(word) == 0;
        } else {
            int res = judge(word.substring(1));
            return res == 1 || res == 0;
        }
    }

    /**
     * 判断传入的字符串是否全为大写或全为小写
     *
     * 全为大写返回 1
     * 全为小写返回 0
     * 大小写混合返回 -1
     */
    private int judge(String str) {
        boolean hasCap = false; // 是否有大写
        boolean hasLow = false; // 是否有小写
        for (char c : str.toCharArray()) {
            if ('a' <= c && c <= 'z') {
                hasLow = true;
            } else {
                hasCap = true;
            }
            if (hasCap && hasLow) {
                return -1;
            }
        }

        return hasCap? 1 : 0;
    }
}
