package other;

import java.util.ArrayList;
import java.util.List;

public class Question60 {
    public static void main(String[] args) {
        System.out.println(getPermutation(3, 6));
    }

    /**
     * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     * 1. "123"
     * 2. "132"
     * 3. "213"
     * 4. "231"
     * 5. "312"
     * 6. "321"
     * 给定 n 和 k，返回第 k 个排列。
     *
     * 说明：
     * 给定 n 的范围是 [1, 9]。
     * 给定 k 的范围是 [1, n!]。
     *
     * @param n
     * @param k
     * @return
     */
    private static String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>(); //存储1...n
        for (int i = 0; i < n; i++) {
            nums.add(i+1);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i < n; i++) {
            int base = factorial(n - i);    //当前以相同数字开头的有几个
            int index = (k-1) / base;                  //表示要取的数在List中的下标
            result.append(nums.get(index));
            nums.remove(index);
            k -= index * base;                  //下一要取的数的位置
        }
        result.append(nums.get(0));

        return result.toString();
    }

    //计算num的阶乘
    private static int factorial(int num) {
        if (num <= 1) {
            return 1;
        }
        int result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }

        return result;
    }
}
