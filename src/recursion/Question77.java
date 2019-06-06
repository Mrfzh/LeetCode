package recursion;

import java.util.ArrayList;
import java.util.List;

public class Question77 {
    public static void main(String[] args) {
        Question77 question77 = new Question77();
        System.out.println(question77.combine_back(4, 2));
    }

    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * 递归
     *
     * @param n
     * @param k
     * @return
     */
    private static List<List<Integer>> combine(int n, int k) {
        //边界判断
        if (n < k || k < 0) {
            return new ArrayList<>();
        } else if (n >= k && k == 0) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }

        return add(1, n, k);
    }

    /**
     * 获得从from...to中所有可能的k个数组合
     *
     * @param from
     * @param to
     * @param k
     */
    private static List<List<Integer>> add(int from, int to, int k) {
        int n = to - from + 1;  //from...to的数字个数
        List<List<Integer>> result = new ArrayList<>();
        if (n == k) {
            List<Integer> list = new ArrayList<>();
            for (int i = from; i <= to; i++) {
                list.add(i);
            }
            result.add(list);
            return result;
        }
        if (k == 1) {
            for (int i = from; i <= to; i++) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                result.add(list);
            }
            return result;
        }

        //当n > k时
        for (int i = 0; i <= n - k; i++) {
            //先递归求得某数前面的数中k-1个数的组合
            List<List<Integer>> other = add(from, to-i-1, k-1);
            //然后加上该数
            for (int j = 0; j < other.size(); j++) {
                List<Integer> curr = other.get(j);
                curr.add(to-i);
                result.add(curr);
            }
        }

        return result;
    }

    private int n;
    private int k;
    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 回溯法
     *
     * 这个是官方题解，一开始比我的递归慢很多，因为有很多不必要的回溯。剪枝后快了很多，和递归时间基本一样。
     */
    private List<List<Integer>> combine_back(int n, int k) {
        this.n = n;
        this.k = k;

        back(new ArrayList<>(), 1);
        return result;
    }

    private void back(List<Integer> curr, int first) {
        if (curr.size() == k) {
            result.add(curr);
            return;
        }

        for (int i = first; i <= n - (k - curr.size()) + 1; i++) {
            List<Integer> list = new ArrayList<>(curr);
            list.add(i);
            back(list, i + 1);
        }
    }
}
