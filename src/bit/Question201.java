package bit;

public class Question201 {
    public static void main(String[] args) {
        System.out.println(new Question201().rangeBitwiseAnd(5, 7));
        System.out.println(new Question201().rangeBitwiseAnd(0, 1));
    }

    /**
     * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，
     * 返回此范围内所有数字的按位与（包含 m, n 两端点）。
     *
     * 位运算
     *
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0 && n == 0) {
            return 0;
        }

        int nBit = 0;     // n 的位数
        int mBit = 0;     // m 的位数
        int flag = (int) Math.pow(2, 30);
        // 计算 n 的位数
        for (int i = 31; i >= 1; i--) {
            if ((flag & n) != 0) {
                nBit = i;
                break;
            }
            flag = flag >> 1;
        }
        flag = (int) Math.pow(2, 30);
        // 计算 m 的位数
        for (int i = 31; i >= 1; i--) {
            if ((flag & m) != 0) {
                mBit = i;
                break;
            }
            flag = flag >> 1;
        }

        // 如果 m 和 n 的位数不等，结果肯定为 0
        if (nBit != mBit) {
            return 0;
        }
        // 如果相等的话，从最高位开始向下遍历
        int res = 0;
        for (int i = nBit; i >= 1; i--) {
            flag = 1 << (i - 1);
            int cn = flag & n;
            int cm = flag & m;

            // 如果当前位相等且不为 0，则在结果中当前位为 1
            if (cm == cn && cm != 0) {
                res += flag;
            }
            // 如果当前为不等，说明之后的位（包括该位）都为 0
            if (cm != cn) {
                break;
            }
        }

        return res;
    }
}
