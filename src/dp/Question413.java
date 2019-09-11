package dp;

public class Question413 {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4};
        System.out.println(new Question413().numberOfArithmeticSlices(A));
    }

    /**
     * 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
     * 函数要返回数组 A 中所有为等差数组的子数组个数。
     *
     * @param A
     * @return
     */
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) {
            return 0;
        }

        int n = A.length;
        int res = 0;
        // 依次计算从 A[i] 开始的等差子数组数量
        for (int i = 0; i < n - 2; i++) {
            int space = A[i+1] - A[i];  // 相差间隔
            // 由于等差数组至少要三个数，所以从第三个数开始算
            for (int j = i+2; j < n; j++) {
                if (A[j] - A[j-1] == space) {
                    res++;
                } else {
                    break;
                }
            }
        }

        return res;
    }
}
