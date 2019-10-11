package recursion;

public class Question526 {
    public static void main(String[] args) {
        System.out.println(new Question526().countArrangement_2(2));
        System.out.println(new Question526().countArrangement_2(10));
    }

    /**
     * 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，
     * 使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。
     * 1. 第 i 位的数字能被 i 整除
     * 2. i 能被第 i 位上的数字整除
     *
     * 现在给定一个整数 N，请问可以构造多少个优美的排列？
     *
     * 说明:
     * N 是一个正整数，并且不会超过15。
     *
     * 递归 + 二进制辅助记忆化搜索
     *
     * @param N
     * @return
     */
    public int countArrangement(int N) {
        helper(1, N, 0);

        return res;
    }

    private int res = 0;

    /**
     * 从 1 到 N，逐个确定数字的位置
     *
     * @param currNum 当前要确定位置的数字
     * @param N
     * @param bit 二进制位，从低到高表示位置 1 到 N，二进制位为 1 表示该位置已有数字
     */
    private void helper(int currNum, int N, int bit) {
        if (currNum == N+1) {
            res++;
            return;
        }

        // 从 1 到 N，确定 currNum 可以放入的位置，可以放入需要满足两个条件：
        // 1. 该位置可以放入
        // 2. 该位置此时没有数字，通过 bit 确认
        for (int i = 1; i <= N; i++) {
            int temp = 1 << (i-1);
            if ((currNum % i == 0 || i % currNum == 0) &&
                    (bit & temp) == 0) {
                helper(currNum+1, N, bit|temp);
            }
        }
    }

    /**
     * 不用二进制位，直接用数组辅助搜索（时间方面差不多，就比上面快几秒）
     */
    public int countArrangement_2(int N) {
        boolean[] isVisited = new boolean[N+1];
        helper_2(1, N, isVisited);

        return res;
    }

    private void helper_2(int currNum, int N, boolean[] isVisited) {
        if (currNum == N+1) {
            res++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if ((currNum % i == 0 || i % currNum == 0) &&
                   !isVisited[i]) {
                isVisited[i] = true;
                helper_2(currNum+1, N, isVisited);
                isVisited[i] = false;   // 这一步很重要，保证下层的递归不影响该层的 isVisited 数组
            }
        }
    }

}
