import java.util.*;

public class Question139 {
    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        System.out.println(new Question139().wordBreak(s, wordDict));
    }

    /**
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
     * 判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     *
     * 说明：
     * 1. 拆分时可以重复使用字典中的单词。
     * 2. 你可以假设字典中没有重复的单词。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return back(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }

    //其中，memo[a]表示由a索引开始的子串是否可以匹配
    private boolean back(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && back(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }
}
