import java.util.Arrays;
import java.util.HashMap;

public class FourFiveFour {
    public static void main(String[] args) {
        int [] A = {0,1,-1};
        int [] B = {-1,1,0};
        int [] C = {0,0,1};
        int [] D = {-1,1,1};
        System.out.println(fourSumCount2(A, B, C, D));
    }

    /**
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
     * 注意：元组元素不能重复
     *
     * 超出时间限制
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
        for (int i = 0; i < C.length; i++) {
            System.out.print(C[i] + "  ");
        }
        System.out.println();
        for (int i = 0; i < D.length; i++) {
            System.out.print(D[i] + "  ");
        }
        System.out.println();
        int result = 0;
        //先固定两个数A[i]和B[i]，再确定另外两个数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                System.out.println("i = " + i + ", j = " + j);
                int sum = A[i] + B[j];
                if (0  < C[0] + D[0] + sum) {    //若C、D集合中的第一个数加起来就大过0，无需再遍历
                    break;
                }
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        int sum2 = C[k] + D[l];
                        if (sum2 + sum > 0) {
                            break;
                        } else if (sum2 + sum == 0) {
//                            System.out.println("i = " + i + ", j = " + j + ", k = " + k + ", l = " + l);
                            result++;
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * 借助HashMap先存储A[i] + B[j]的值，再比较C[k] + C[l]
     */
    private static int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;
        if (n < 1) {
            return 0;
        }

        HashMap<Integer, Integer> hashMap = new HashMap<>();    //key为A[i] + B[j]的值，value为该值的组合个数
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = A[i] + B[j];
                if (hashMap.containsKey(key)) {
                    hashMap.put(key, hashMap.get(key) + 1);
                } else {
                    hashMap.put(key, 1);
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                int need = - (C[k] + D[l]);  //加起来为0所需要的key
                if (hashMap.containsKey(need)) {
                    result += hashMap.get(need);
                }
            }
        }

        return result;
    }
}
