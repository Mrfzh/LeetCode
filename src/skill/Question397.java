package skill;

public class Question397 {
    public static void main(String[] args) {
        System.out.println(new Question397().integerReplacement_2(8));
        System.out.println(new Question397().integerReplacement_2(7));
        System.out.println(new Question397().integerReplacement_2(2147483647));
    }

    /**
     * 给定一个正整数 n，你可以做如下操作：
     * 1. 如果 n 是偶数，则用 n / 2替换 n。
     * 2. 如果 n 是奇数，则可以用 n + 1或n - 1替换 n。
     *
     * n 变为 1 所需的最小替换次数是多少？
     *
     * @param n
     * @return
     */
    public int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }

        if ((n & 1) == 0) {
            // 偶数情况
            return integerReplacement(n >> 1) + 1;
        } else {
            // 奇数情况
            // 留意 int 最大值的情况
            if (n == Integer.MAX_VALUE) {
                return integerReplacement(n-1);     // 不用加 1
            } else {
                return Math.min(integerReplacement(n+1),
                        integerReplacement(n-1)) + 1;
            }
        }
    }

    /**
     * 非递归版本
     */
    public int integerReplacement_2(int n) {
        int res = 0;
        long temp = n;  // 利用 long 保存 n，是为了应对 n 为 Integer.Max 的情况
        while (temp != 1) {
            if ((temp & 1) == 0) {
                // 偶数情况
                temp = temp >> 1;
            } else {
                // 奇数情况
                if ((temp & 2) == 2 && temp != 3) {
                    // 如果第二位为 1，且 n 不为 3，应该加 1，将第二位变为 0
                    temp++;
                } else {
                    temp--;
                }
            }
            res++;
        }

        return res;
    }
}
