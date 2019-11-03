package dp;

public class Question647 {
    public static void main(String[] args) {
        System.out.println(new Question647().countSubstrings("abcbacbbbcaa"));
    }

    /**
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     *
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
     *
     * dp
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int n = s.length();
        if (n <= 1) {
            return n;
        }

        // dp[a][b] 表示 s[a] 到 s[b] 间的子串是否为回文
        boolean[][] dp = new boolean[n][n];
        // 存储结果
        int res = 0;
        // 初始化
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        res += n;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;
                res++;
            }
        }
        // 递推
        for (int i = 3; i <= n; i++) {  // 该层控制子串的长度
            for (int j = 0; j <= n - i; j++) {
                // 判断 s[j] 到 s[j+i-1] 之间是否为回文
                // s[j] 到 s[j+i-1] 为回文的条件为：s[j+1][j+i-2] 为回文且 s[j] == s[j+i-1]
                dp[j][j+i-1] = dp[j+1][j+i-2] && s.charAt(j) == s.charAt(j+i-1);
                if (dp[j][j+i-1]) {
                    res++;
                }
            }
        }

        return res;
    }
}
