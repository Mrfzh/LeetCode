package dp;

public class Question516 {
    public static void main(String[] args) {
        System.out.println(new Question516().longestPalindromeSubseq("cbbd"));
    }

    /**
     * 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        // dp[a][b] 表示 s[a ... b] 之间的最长回文子序列长度
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        // 以长度为基准由低到高进行递推
        for (int i = 2; i <= s.length(); i++) {
            for (int j = 0; j < s.length()-i+1; j++) {
                if (s.charAt(j) == s.charAt(j+i-1)) {
                    // 如果左右边界的值相等
                    dp[j][j+i-1] = 2 + dp[j+1][j+i-2];
                } else {
                    // 如果不等则去掉边界，取较大值
                    dp[j][j+i-1] = Math.max(dp[j+1][j+i-1], dp[j][j+i-2]);
                }
            }
        }

        return dp[0][s.length()-1];
    }
}
