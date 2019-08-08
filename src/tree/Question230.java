package tree;

import java.util.Stack;

public class Question230 {
    public static void main(String[] args) {
        Integer[] values = {5,3,6,2,4,null,null,1};
        TreeNode root = TreeNodeUtil.buildTree(values);
        System.out.println(new Question230().kthSmallest(root, 3));
    }

    /**
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     *
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        int count = 0;
        int res = 0;

        // 保存父节点（中间节点）
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        while (curr != null) {
            if (curr.left != null) {
                stack.add(curr);
                curr = curr.left;
            } else {
                count++;
                if (count == k) {
                    return curr.val;
                }
                if (curr.right != null) {
                    curr = curr.right;
                } else {
                    boolean hasFound = false;
                    while (!stack.isEmpty()) {
                        TreeNode node = stack.pop();
                        count++;
                        if (count == k) {
                            return node.val;
                        }
                        if (node.right != null) {
                            curr = node.right;
                            hasFound = true;
                            break;
                        }
                    }
                    if (!hasFound) {
                        curr = null;
                    }
                }
            }
        }

        return res;
    }

    private int count;
    private int target;
    private int res;
    private boolean hasFound = false;

    /**
     * 递归解法（比非递归快）
     */
    public int kthSmallest_recursion(TreeNode root, int k) {
        count = 0;
        target = k;
        find(root);

        return res;
    }

    private void find(TreeNode root) {
        if (root == null || hasFound) {
            return;
        }
        find(root.left);
        count++;
        if (count == target) {
            res = root.val;
            hasFound = true;
            return;
        }
        find(root.right);
    }
}
