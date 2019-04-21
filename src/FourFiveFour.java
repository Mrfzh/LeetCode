import java.util.Arrays;

public class FourFiveFour {
    public static void main(String[] args) {
        int [] A = {0,1,-1};
        int [] B = {-1,1,0};
        int [] C = {0,0,1};
        int [] D = {-1,1,1};
        System.out.println(fourSumCount(A, B, C, D));
    }

    /**
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
     * 注意：元组元素不能重复
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return 元组个数
     */
    private static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;   //各数组的元素个数
        if (n < 1) {
            return 0;
        }

        //先排序
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);
        int result = 0;
        //先固定两个数A[i]和B[i]，再确定另外两个数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = A[i] + B[j];
                if (0 - sum < C[0] + D[0]) {    //若C、D集合中的第一个数加起来就大过0，无需再遍历
                    break;
                }
                int l = 0;
                int r = n-1;
                while (l < n && r >= 0) {
                    int sum2 = C[l] + D[r];
                    if (sum + sum2 == 0) {
                        result++;
                        int l1 = 1;
                        l++;
                        while (l < n && C[l] == C[l-1]) {
                            l1++;
                            l++;
                        }
                        int r1 = 1;
                        r--;
                        while (r >=0 && C[r] == C[r+1]) {
                            r1++;
                            r--;
                        }
                        result += l1 * r1 - 1;
                    } else if (sum + sum2 > 0) {
                        r--;
                    } else {
                        l++;
                    }
                }
            }
        }

        return result;
    }
}
