package array;

public class Question576 {
    public static void main(String[] args) {
        System.out.println(new Question576().findPaths(36,
                5,
                50,
                15,
                3));
    }

    /**
     * 给定一个 m × n 的网格和一个球。球的起始坐标为 (i,j) ，
     * 你可以将球移到相邻的单元格内，或者往上、下、左、右四个方向上移动使球穿过网格边界。
     * 但是，你最多可以移动 N 次。
     * 找出可以将球移出边界的路径数量。答案可能非常大，返回 结果 mod 10^9 + 7 的值。
     *
     * 说明:
     * 1. 球一旦出界，就不能再被移动回网格内。
     * 2. 网格的长度和高度在 [1,50] 的范围内。
     * 3. N 在 [0,50] 的范围内。
     *
     * 利用二维数组记录中间过程
     *
     */
    public int findPaths(int m, int n, int N, int i, int j) {
        // record[a][b] 表示当前在 (a, b) 位置的球数
        // （假设从某位置由四个方向向隔壁移动后，每个方向的相邻位置都多了一个球）
        long[][] record = new long[m][n];
        record[i][j] = 1;

        long res = 0;
        long a = (long) (Math.pow(10f, 9f) + 7);
        // N 取 1,2，...
        for (int k = 1; k <= N; k++) {
            // 先 copy 一份副本，后面的判断在副本上做
            long[][] copy = new long[m][n];
            for (int l = 0; l < m; l++) {
                for (int o = 0; o < n; o++) {
                    copy[l][o] = record[l][o];
                }
            }
            for (int l = 0; l < m; l++) {
                for (int o = 0; o < n; o++) {
                    if (copy[l][o] != 0) {
                        long num = copy[l][o];
                        // 分别向四个方向的邻位移动
                        // 注意每次移动后都要取模，不然可能会溢出
                        if (l+1 < m) {
                            record[l+1][o] += num;
                            record[l+1][o] = helper(record[l+1][o], a);
                        } else {
                            res += num;
                            res = helper(res, a);
                        }
                        if (l-1 >= 0) {
                            record[l-1][o] += num;
                            record[l-1][o] = helper(record[l-1][o], a);
                        } else {
                            res += num;
                            res = helper(res, a);
                        }
                        if (o+1 < n) {
                            record[l][o+1] += num;
                            record[l][o+1] = helper(record[l][o+1], a);
                        } else {
                            res += num;
                            res = helper(res, a);
                        }
                        if (o-1 >= 0) {
                            record[l][o-1] += num;
                            record[l][o-1] = helper(record[l][o-1], a);
                        } else {
                            res += num;
                            res = helper(res, a);
                        }
                        // 当前位置减 num
                        record[l][o] = Math.floorMod(record[l][o] - num, a);
                    }
                }
            }
        }

        return (int) res;
    }

    /**
     * 返回 num mod a 的结果
     */
    private long helper(long num, long a) {
        if (num >= a) {
            num = Math.floorMod(num, a);
        }
        return num;
    }
}
