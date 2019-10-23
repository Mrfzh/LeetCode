package dp;

public class Question583 {
    public static void main(String[] args) {
        System.out.println(new Question583().minDistance("sea", "eat"));
    }

    /**
     * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，
     * 每步可以删除任意一个字符串中的一个字符。
     *
     * 说明:
     * 给定单词的长度不超过500。
     * 给定单词中的字符只含有小写字母。
     *
     * dp
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if (word1.isEmpty() || word2.isEmpty()) {
            return word1.length() + word2.length();
        }
        // 题目可转换为求 word1 和 word2 的最长相同子序列的长度 n
        // 最后返回 word1.len + word2.len - 2*n

        // dp[a][b] 表示 word1[0 ... a) 和 word2[0 ... b) 的最长相同子序列长度
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    // 如果 word1[i-1] == word2[j-1]，那么将 dp[i-1][j-1] 的结果加一即可
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    // 如果 word1[i-1] != word2[j-1]，那么就看下不要哪个值取得的效果更好
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return word1.length() + word2.length() - 2 * dp[word1.length()][word2.length()];
    }
}
