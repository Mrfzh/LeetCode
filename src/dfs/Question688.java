package dfs;

public class Question688 {
    public static void main(String[] args) {

    }

    /**
     * 已知一个 NxN 的国际象棋棋盘，棋盘的行号和列号都是从 0 开始。
     * 即最左上角的格子记为 (0, 0)，最右下角的记为 (N-1, N-1)。 
     *
     * 现有一个 “马”（也译作 “骑士”）位于 (r, c) ，并打算进行 K 次移动。 
     * 现在 “马” 每一步都从可选的位置（包括棋盘外部的）中独立随机地选择一个进行移动，
     * 直到移动了 K 次或跳到了棋盘外面。
     *
     * 求移动结束后，“马” 仍留在棋盘上的概率。
     *
     * 注意：
     * N 的取值范围为 [1, 25]
     * K 的取值范围为 [0, 100]
     * 开始时，“马” 总是位于棋盘上
     *
     * dfs + 记忆化，用时 1098 ms，时间好长，勉强过了。
     *
     * @param N
     * @param K
     * @param r
     * @param c
     * @return
     */
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] record = new double[N][N][K];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < K; k++) {
                    record[i][j][k] = -1;
                }
            }
        }
        return dfs(N, K, r, c, record);
    }

    private double dfs(int N, int K, int r, int c, double[][][] record) {
        if (K == 0) {
            return 1;
        }
        if (record[r][c][K] != -1) {
            return record[r][c][K];
        }
        double res = 0;
        // 判断下一跳的位置是否合法
        res += isValid(N, r-1, c-2)? dfs(N, K-1, r-1, c-2, record) : 0;
        res += isValid(N, r-2, c-1)? dfs(N, K-1, r-2, c-1, record) : 0;
        res += isValid(N, r-2, c+1)? dfs(N, K-1, r-2, c+1, record) : 0;
        res += isValid(N, r-1, c+2)? dfs(N, K-1, r-1, c+2, record) : 0;
        res += isValid(N, r+1, c-2)? dfs(N, K-1, r+1, c-2, record) : 0;
        res += isValid(N, r+2, c-1)? dfs(N, K-1, r+2, c-1, record) : 0;
        res += isValid(N, r+2, c+1)? dfs(N, K-1, r+2, c+1, record) : 0;
        res += isValid(N, r+1, c+2)? dfs(N, K-1, r+1, c+2, record) : 0;

        res = res / 8;
        record[r][c][K] = res;
        return res;
    }

    private boolean isValid(int N, int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

}
