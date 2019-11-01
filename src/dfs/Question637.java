package dfs;

import java.util.ArrayList;
import java.util.List;

public class Question637 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
     *
     * 递归 dfs
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();  // 存储每一层的节点值
        helper(root, 0, lists);
        List<Double> res = new ArrayList<>();
        for (List<Integer> list : lists) {
            double sum = 0;
            for (int val : list) {
                sum += val;
            }
            res.add(sum / list.size());
        }
        return res;
    }

    private void helper(TreeNode root, int level, List<List<Integer>> lists) {
        if (root == null) {
            return;
        }
        if (lists.size() == level) {
            lists.add(new ArrayList<>());
        }
        // 存储该节点的值
        lists.get(level).add(root.val);

        helper(root.left, level + 1, lists);
        helper(root.right, level + 1, lists);
    }
}
