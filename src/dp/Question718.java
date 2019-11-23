package dp;

public class Question718 {
    public static void main(String[] args) {
        int[] A = {1,2,3,2,1};
        int[] B = {3,2,1,4,7};
        System.out.println(new Question718().findLength(A, B));
    }

    /**
     * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
     *
     * 说明:
     * 1 <= len(A), len(B) <= 1000
     * 0 <= A[i], B[i] < 100
     *
     * dp
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        // dp[a][b] 表示以 A[a-1] 和 B[b-1] 结尾的公共最长连续子数组
        int[][] dp = new int[A.length+1][B.length+1];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                dp[i][j] = A[i-1] == B[j-1]? 1+dp[i-1][j-1] : 0;
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }
}
