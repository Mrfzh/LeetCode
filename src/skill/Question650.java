package skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Question650 {
    public static void main(String[] args) {
        System.out.println(new Question650().minSteps(2));
        System.out.println(new Question650().minSteps(3));
        System.out.println(new Question650().minSteps(1000));
    }

    /**
     * 最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
     * Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
     * Paste (粘贴) : 你可以粘贴你上一次复制的字符。
     *
     * 给定一个数字 n 。你需要使用最少的操作次数，
     * 在记事本中打印出 恰好  n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。
     *
     * @param n
     * @return
     */
    public int minSteps(int n) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        return helper(n, hashMap);
    }

    /**
     * 求恰好打出 n 个 A 所需的最小次数，hashMap 进行记忆化
     */
    private int helper(int n, HashMap<Integer, Integer> hashMap) {
        // 边界
        if (n == 1) {
            return 0;
        }
        if (hashMap.containsKey(n)) {
            return hashMap.get(n);
        }

        // 注意：由于要恰好打出 n 个 'A'，所以复制的个数必须是 n 的因子

        // 先找出 n 的因子，不包括自身
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                list.add(i);
            }
        }
        int min = Integer.MAX_VALUE;
        // 遍历因子，计算当最后复制的数为某因子时，所需的最小次数
        for (int lastNum : list) {
            min = Math.min(min, helper(lastNum, hashMap) + n / lastNum);
        }

        hashMap.put(n, min);
        return min;
    }
}
