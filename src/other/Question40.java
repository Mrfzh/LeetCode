package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Question40 {
    public static void main(String[] args) {
        int[] candidates = {2,5,2,1,2};
        System.out.println(combinationSum2(candidates, 5));
    }

    /**
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用一次。
     *
     * 说明：
     * 1. 所有数字（包括目标数）都是正整数。
     * 2. 解集不能包含重复的组合。
     *
     * @param candidates
     * @param target
     * @return
     */
    private static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        HashSet<List<Integer>> result = new HashSet<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);    //一定要先排序
        find(result, temp, target, candidates, 0);

        return new ArrayList<>(result);
    }

    private static void find(HashSet<List<Integer>> result, List<Integer> temp, int target, int[] candidates, int start) {
        if (target == 0) {
            result.add(temp);
            return;
        }

        for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
            List<Integer> list = new ArrayList<>(temp);
            list.add(candidates[i]);
            int next = i + 1;
            find(result, list, target-candidates[i], candidates, next);
        }
    }

}
