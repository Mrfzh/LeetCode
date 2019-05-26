package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question39 {
    public static void main(String[] args) {
        int[] candidates = {2,3,5};
        System.out.println(combinationSum2(candidates, 10));
    }

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * 注意：candidates 中的数字可以无限制重复被选取。
     *
     *  开始自己写的递归
     *
     * @param candidates
     * @param target
     * @return
     */
    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int n = candidates.length;
        if (n < 1) {
            return result;
        }
        if (n == 1) {
            if (target % candidates[0] == 0) {
                List<Integer> list = new ArrayList<>();
                int num = target / candidates[0];
                for (int i = 0; i < num; i++) {
                    list.add(candidates[0]);
                }
                result.add(list);
            }
            return result;
        }

        int left = 0;
        int leftNum = 0;
        while (left < target) {
            List<List<Integer>> other = combinationSum(Arrays.copyOfRange(candidates, 1, n), target-left);
            if (other.size() != 0) {
                for (int i = 0; i < other.size(); i++) {
                    List<Integer> curr = other.get(i);
                    for (int j = 0; j < leftNum; j++) {
                        curr.add(candidates[0]);
                    }
                    result.add(curr);
                }
            }
            left += candidates[0];
            leftNum++;
        }
        //加上全是第一个数的情况
        if (target % candidates[0] == 0) {
            List<Integer> list = new ArrayList<>();
            int num = target / candidates[0];
            for (int i = 0; i < num; i++) {
                list.add(candidates[0]);
            }
            result.add(list);
        }

        return result;
    }

    /**
     * 优化后的递归
     */
    private static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        find(result, temp, target, candidates, 0);

        return result;
    }

    /**
     * 从candidates[start]开始，寻找数组中的元素，使其加入已有组合temp后，和为target，并将这些新组合存入result
     *
     * @param result
     * @param temp
     * @param target
     * @param candidates
     * @param start
     */
    private static void find(List<List<Integer>> result, List<Integer> temp, int target, int[] candidates, int start) {
        if (target == 0) {
            result.add(temp);
            return;
        }

        for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
            //对temp进行深拷贝，这是为了不影响这一层其他的递归
            List<Integer> list = new ArrayList<>(temp);
            list.add(candidates[i]);
            //进行递归
            find(result, list, target-candidates[i], candidates, i);
        }
    }
}
