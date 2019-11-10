package skill;

import java.util.Arrays;

public class Question667 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Question667().constructArray(3,2)));
    }

    /**
     * 给定两个整数 n 和 k，你需要实现一个数组，这个数组包含从 1 到 n 的 n 个不同整数，
     * 同时满足以下条件：
     * ① 如果这个数组是 [a1, a2, a3, ... , an] ，那么数组 
     * [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数；.
     * ② 如果存在多种答案，你只需实现并返回其中任意一种.
     *
     * 提示: n 和 k 满足条件 1 <= k < n <= 10^4.
     *
     * 找规律，找到符合要求的格式即可
     *
     * @param n
     * @param k
     * @return
     */
    public int[] constructArray(int n, int k) {
        // 一个满足条件的序列为 1,last + k,last - (k - 1),last + (k - 2),...,直到 k = 1，
        // 之后的按照 k+2,k+3,...n 顺序排列即可
        int[] res = new int[n];
        res[0] = 1;
        // res[i] = res[i-1] + a
        int a = k;
        for (int i = 1; i <= k; i++) {
            res[i] = res[i-1] + a;
            a = a < 0? - a - 1 : - a + 1;
        }
        // res[i] = i+1
        for (int i = k+1; i < res.length; i++) {
            res[i] = i+1;
        }

        return res;
    }
}
