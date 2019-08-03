package back;

import java.util.ArrayList;
import java.util.List;

public class Question216 {
    public static void main(String[] args) {
        System.out.println(new Question216().combinationSum3(3, 9));
    }

    private List<List<Integer>> res = new ArrayList<>();

    /**
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，
     * 并且每种组合中不存在重复的数字。
     *
     * 回溯
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (((19 - k) * k) / 2 < n || ((1 + k) * k) / 2 > n) {
            return new ArrayList<>();
        }

        find(new ArrayList<>(), n, k, 1);

        return res;
    }

    /**
     * 从 start 开始查找，找到满足 target 所需的 num 个数
     *
     * @param curr 当前集合
     * @param target 当前所需满足的数
     * @param num 当前所需的数字个数
     * @param start 从 start 开始查找（包括 start）
     */
    private void find(List<Integer> curr, int target, int num, int start) {
        if (target == 0 && num == 0) {
            res.add(curr);
            return;
        }
        if (target == 0 || num == 0 || start > 9) {
            return;
        }

        for (int i = start; i < 10; i++) {
            if (i <= target) {
                List<Integer> list = new ArrayList<>(curr);
                list.add(i);
                find(list, target - i, num - 1, i + 1);
            } else {
                break;
            }
        }
    }
}
