package dp;

public class Question712 {
    public static void main(String[] args) {
        System.out.println(new Question712().minimumDeleteSum("delete", "leet"));
    }

    /**
     * 给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
     *
     * 注意:
     * 0 < s1.length, s2.length <= 1000。
     * 所有字符串中的字符ASCII值在[97, 122]之间。
     *
     * dp
     *
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum(String s1, String s2) {
        // dp[a][b] 表示使 s1[a:]（从 s1[a] 到末尾）和 s2[b:]（从 s2[b] 到末尾）
        // 相等所需删除字符的 ASCII 值的最小和
        int[][] dp = new int[s1.length()+1][s2.length()+1];

        // 初始化：s2 或 s1 为空的情况
        for (int i = s1.length()-1; i >= 0; i--) {
            dp[i][s2.length()] = dp[i+1][s2.length()] + s1.codePointAt(i);
        }
        for (int i = s2.length()-1; i >= 0; i--) {
            dp[s1.length()][i] = dp[s1.length()][i+1] + s2.codePointAt(i);
        }

        // 递推（由大推小）
        for (int i = s1.length()-1; i >= 0; i--) {
            for (int j = s2.length()-1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) { // 无需删除字符
                    dp[i][j] = dp[i+1][j+1];
                } else {    // 看下是删除 s1[i] 好还是删除 s2[j] 好
                    dp[i][j] = Math.min(dp[i+1][j] + s1.codePointAt(i),
                            dp[i][j+1] + s2.codePointAt(j));
                }
            }
        }

        return dp[0][0];
    }
}
