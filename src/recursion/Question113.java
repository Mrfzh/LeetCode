package recursion;

import java.util.ArrayList;
import java.util.List;

public class Question113 {
    public static void main(String[] args) {

    }

    List<List<Integer>> res = new ArrayList<>();

    /**
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 递归
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return new ArrayList<>();
        }

        find(new ArrayList<>(), 0, sum, root);

        return res;
    }

    /**
     * 从start节点开始，找到一条到叶子节点且和为sum的路径，currSun为当前和，currList为当前list
     */
    private void find(List<Integer> currList, int currSum, int sum, TreeNode start) {
        if (start.left == null && start.right == null) {
            if (currSum + start.val == sum) {
                currList.add(start.val);
                res.add(currList);
            }
            return;
        }

        currSum += start.val;
        currList.add(start.val);

        if (start.left == null) {
            //左子树为空，则遍历右子树
            find(new ArrayList<>(currList), currSum, sum, start.right);
        } else if (start.right == null) {
            //右子树为空，则遍历左子树
            find(new ArrayList<>(currList), currSum, sum, start.left);
        } else {
            //左右子树都不为空，都遍历
            find(new ArrayList<>(currList), currSum, sum, start.left);
            find(new ArrayList<>(currList), currSum, sum, start.right);
        }
    }
}
