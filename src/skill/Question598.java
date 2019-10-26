package skill;

public class Question598 {
    public static void main(String[] args) {
        int[][] ops = {{2,2},{3,3}};
        System.out.println(new Question598().maxCount(3,3,ops));
    }

    /**
     * 给定一个初始元素全部为 0，大小为 m*n 的矩阵 M 以及在 M 上的一系列更新操作。
     * 操作用二维数组表示，其中的每个操作用一个含有两个正整数 a 和 b 的数组表示，
     * 含义是将所有符合 0 <= i < a 以及 0 <= j < b 的元素 M[i][j] 的值都增加 1。
     *
     * 在执行给定的一系列操作后，你需要返回矩阵中含有最大整数的元素个数。
     *
     * 技巧题
     *
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public int maxCount(int m, int n, int[][] ops) {
        // 问题可转换为求各操作中 a 和 b 的最小值 aMin, bMin
        // 然后结果为 aMin * bMin
        int aMin = m;
        int bMin = n;
        for (int[] op : ops) {
            aMin = Math.min(aMin, op[0]);
            bMin = Math.min(bMin, op[1]);
        }

        return aMin * bMin;
    }
}
