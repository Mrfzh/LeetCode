package recursion;

public class Question779 {
    public static void main(String[] args) {
        System.out.println(new Question779().kthGrammar(4,5));
    }

    /**
     * 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
     *
     * 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）
     *
     * 注意：
     * N 的范围 [1, 30].
     * K 的范围 [1, 2^(N-1)].
     *
     * 递归
     *
     * @param N
     * @param K
     * @return
     */
    public int kthGrammar(int N, int K) {
        if (N == 1) {
            return 0;
        }
        int pre = kthGrammar(N-1, (K+1)/2);

        if (pre == 0) {
            return (K & 1) == 1? 0 : 1;
        } else {
            return (K & 1) == 1? 1 : 0;
        }
    }
}
