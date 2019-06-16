package stack;

import java.util.*;

public class Question94 {
    public static void main(String[] args) {
        TreeNode t3 = new TreeNode(3);
        TreeNode t2 = new TreeNode(2);
        t2.left = t3;
        t2.right = null;
        TreeNode t1 = new TreeNode(1);
        t1.left = null;
        t1.right = t2;
        System.out.println(new Question94().inorderTraversal(t1));
        System.out.println(new Question94().inorderTraversal_recursion(t1));
    }

    /**
     * 给定一个二叉树，返回它的中序 遍历
     *
     * 基于栈的非递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        HashSet<TreeNode> hasFound = new HashSet<>();   //存入已遍历过的节点
        hasFound.add(root);

        while (stack.size() != 0) {
            TreeNode pop = stack.getFirst();    //栈顶元素
            //若栈顶元素的左孩子不为空，且没遍历过，将其入栈
            if (pop.left != null && !hasFound.contains(pop.left)) {
                stack.push(pop.left);
                hasFound.add(pop.left);
            } else {
                res.add(stack.pop().val);   //取出栈顶元素
                //当左孩子为空时，若栈顶元素的右孩子不为空，且没遍历过，将其入栈
                if (pop.right != null && !hasFound.contains(pop.right)) {
                    stack.push(pop.right);
                    hasFound.add(pop.right);
                }
            }
        }

        return res;
    }

    /**
     * 递归
     */
    public List<Integer> inorderTraversal_recursion(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        if (root.left != null) {
            res.addAll(inorderTraversal_recursion(root.left));
        }
        res.add(root.val);
        if (root.right != null) {
            res.addAll(inorderTraversal_recursion(root.right));
        }

        return res;
    }

    /**
     * 栈的优化
     */
    public List<Integer> inorderTraversal_stack(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;   //存储当前遍历到的节点
        while (curr != null || !stack.isEmpty()) {
            //若当前节点不为空，将当前节点及其下面的左孩子都入栈
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            //此时curr为空，栈顶节点出栈
            curr = stack.pop();
            res.add(curr.val);
            //开始遍历栈顶节点的右孩子
            curr = curr.right;
        }

        return res;
    }
}
