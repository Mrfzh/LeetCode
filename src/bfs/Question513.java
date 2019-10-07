package bfs;

import java.util.LinkedList;

public class Question513 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个二叉树，在树的最后一行找到最左边的值。
     *
     * 注意: 您可以假设树（即给定的根节点）不为 NULL。
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        TreeNode left = null;   // 已遍历节点中的上一最左节点
        TreeNode right = null;  // 已遍历节点中的上一最右节点
        TreeNode lastNode = null;   // 上一遍历节点
        TreeNode temp = null;   // 暂存下一层最右节点
        TreeNode nextRight = root;  // 下一层的最右节点
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            // 上一最右节点的下一节点为最左节点
            if (right == null || lastNode == right) {
                left = curr;
            }
            // 子节点加入队列
            if (curr.left != null) {
                queue.add(curr.left);
                temp = curr.left;
            }
            if (curr.right != null) {
                queue.add(curr.right);
                temp = curr.right;
            }
            // 当前节点为下一最右节点时
            if (curr == nextRight) {
                right = nextRight;
                nextRight = temp;
            }
            lastNode = curr;
        }

        return left.val;
    }

    /**
     * 简化上述代码
     */
    public int findBottomLeftValue_simplify(TreeNode root) {
        int res = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int count = queue.size();   // 本层节点数
            res = queue.peek().val;     // 记录本层最左节点的值
            while (count > 0) {
                TreeNode curr = queue.remove();
                // 子节点加入队列
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
                count--;
            }
        }

        return res;
    }
}
