package dp;

import java.util.Arrays;
import java.util.HashMap;

public class Question474 {
    public static void main(String[] args) {
        String[] strs = {"11","11","0","0","10","1","1","0","11","1","0","111","11111000","0","11",
                "000","1","1","0","00","1","101","001","000","0","00","0011","0","10000"};
        System.out.println(new Question474().findMaxForm_dp(strs, 90, 66));
    }

    // 存储结果
    private int res = 0;

    /**
     * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
     *
     * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。
     * 每个 0 和 1 至多被使用一次。
     *
     * 递归超时
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs.length <= 0) {
            return 0;
        }

        // 先比较 m 和 n 的大小，若 m 小则以 0 为基准，否则以 1 为基准
        char pivot = m < n? '0' : '1';

        // HashMap 中的 key 存储字符串，value 存储该字符串中基准（0 或 1）的数量
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String str : strs) {
            int count = 0;
            for (char c : str.toCharArray()) {
                if (c == pivot) {
                    count++;
                }
            }
            hashMap.put(str, count);
        }

        // 对字符串数组进行排序，按照字符串中基准的个数排序
        Arrays.sort(strs, (o1, o2) -> hashMap.get(o1) - hashMap.get(o2));

        // 递归寻找
        int pn = m < n? m : n;
        int an = m < n? n : m;
        find(strs, 0, pn, an, 0, hashMap);

        return res;
    }

    /**
     * 从 strs[start] 开始查找，查找能拼出 strs 数组中的字符串的最大数量
     *
     * pn 为 可使用的 pivot 的个数, an 为可使用的另一个字符的个数
     * currNum 为当前已拼出的字符个数，hashMap 的作用上面有说明
     */
    private void find(String[] strs, int start, int pn, int an,
                      int currNum, HashMap<String, Integer> hashMap) {
        // 边界判断
        if (start >= strs.length || hashMap.get(strs[start]) > pn) {
            res = Math.max(res, currNum);
            return;
        }

        for (int i = start; i < strs.length; i++) {
            int needPn = hashMap.get(strs[i]);
            // pn 不能满足，退出循环
            if (needPn > pn) {
                break;
            }
            // 还有判断 an 是否满足
            int needAn = strs[i].length() - needPn;
            // an 也满足时，说明当前字符串符合要求，继续寻找下一个
            if (needAn <= an) {
                find(strs, i+1, pn - needPn, an - needAn,
                        currNum + 1, hashMap);
            }
        }
    }

    /**
     * 背包问题，用 dp 解决
     */
    public int findMaxForm_dp(String[] strs, int m, int n) {
        if (m < 0 || n < 0) {
            return 0;
        }

        // dp[a][b] 表示有 a 个 0 和 b 个 1 时，能够组成的最多字符串个数
        int[][] dp = new int[m+1][n+1];
        for (String str : strs) {
            // 先计算当前字符串中 0 和 1 的个数
            int zeroCount = 0;
            int oneCount = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }

            // 递推
            // 注意：这里必须从后往前遍历，而不能从前往后遍历
            // 因为在推出 dp 的值时，是较大值用到较小值
            // 所以如果从前往后遍历（由小到大），较小值在前面的遍历中可能已经被修改了
            // 会影响之后的较大值推出的结果，所以要从后往前遍历
            for (int i = m; i >= zeroCount; i--) {
                for (int j = n; j >= oneCount; j--) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - zeroCount][j - oneCount]);
                }
            }
        }

        return dp[m][n];
    }
}
