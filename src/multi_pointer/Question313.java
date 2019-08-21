package multi_pointer;

import java.util.ArrayList;
import java.util.List;

public class Question313 {
    public static void main(String[] args) {
        int[] primes = {2,7,13,19};
        System.out.println(new Question313().nthSuperUglyNumber(12, primes));
    }

    /**
     * 编写一段程序来查找第 n 个超级丑数。
     * 超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
     *
     * 说明:
     * 1. 1 是任何给定 primes 的超级丑数。
     * 2. 给定 primes 中的数字以升序排列。
     *
     * 多指针
     *
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        // 超级丑数序列
        int[] sun = new int[n];
        sun[0] = 1;
        // 指针序列，point[a] 表示 primes[a] 在 sun 数组的指向
        int[] point = new int[primes.length];

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            List<Integer> minIndexList = new ArrayList<>(); // 可能有多个指针需要移动
            // 找到下一个超级丑数
            for (int j = 0; j < point.length; j++) {
                // 该指针对应的下一个超级丑数
                long curr = sun[point[j]] * primes[j];
                if (curr < min) {
                    min = (int) curr;
                    minIndexList.clear();
                    minIndexList.add(j);
                } else if (curr == min) {
                    minIndexList.add(j);
                }
            }
            // 对应指针后移
            for (int minIndex : minIndexList) {
                point[minIndex]++;
            }
            // 下一超级丑数
            sun[i] = min;
        }

        return sun[n-1];
    }
}
