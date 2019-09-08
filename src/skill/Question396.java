package skill;

public class Question396 {
    public static void main(String[] args) {
        int[] A = {4, 3, 2, 6};
        System.out.println(new Question396().maxRotateFunction(A));
    }

    /**
     * 给定一个长度为 n 的整数数组 A 。
     * 假设 Bk 是数组 A 顺时针旋转 k 个位置后的数组，我们定义 A 的“旋转函数” F 为：
     * F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1]。
     *
     * 计算F(0), F(1), ..., F(n-1)中的最大值。
     *
     * @param A
     * @return
     */
    public int maxRotateFunction(int[] A) {
        // 先计算 0*A[0] + 1*A[1] + ... + (n-1)*A[n-1]，先假设其为最大值
        // 以及计算 A[0] 到 A[n-1] 的和
        int max = 0;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            max += i*A[i];
            sum += A[i];
        }
        int temp = max;
        // 计算其他结果
        for (int i = 0; i < A.length - 1; i++) {
            temp = temp - sum + A.length*A[i];  // 重点：其他结果的计算
            max = Math.max(max, temp);
        }

        return max;
    }
}
