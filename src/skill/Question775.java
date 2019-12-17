package skill;

public class Question775 {
    public static void main(String[] args) {
        int[] A = {1,2,0};
        System.out.println(new Question775().isIdealPermutation(A));
    }

    /**
     * 数组 A 是 [0, 1, ..., N - 1] 的一种排列，N 是数组 A 的长度。
     * 全局倒置指的是 i,j 满足 0 <= i < j < N 并且 A[i] > A[j] ，
     * 局部倒置指的是 i 满足 0 <= i < N 并且 A[i] > A[i+1] 。
     *
     * 当数组 A 中全局倒置的数量等于局部倒置的数量时，返回 true 。
     *
     * 注意:
     * A 是 [0, 1, ..., A.length - 1] 的一种排列
     * A 的长度在 [1, 5000]之间
     *
     * @param A
     * @return
     */
    public boolean isIdealPermutation(int[] A) {
        // 问题可以转换为; 是否存在 a[i] > a[i+n]（n >= 2）
        if (A.length <= 2) {
            return true;
        }
        int max = A[0];    // 对于 a[i]，这表示 a[0...i-2] 的最大值
        for (int i = 2; i < A.length; i++) {
            if (max > A[i]) {
                return false;
            }
            max = Math.max(max, A[i-1]);
        }

        return true;
    }
}
