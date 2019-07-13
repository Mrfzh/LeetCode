import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Question144 {
    public static void main(String[] args) {
        Integer[] values = {1, null, 2, 3};
        TreeNode head = TreeNodeUtil.buildTree(values);
        System.out.println(new Question144().preorderTraversal(head));
    }

    /**
     * 给定一个二叉树，返回它的 前序 遍历。要求用迭代完成。
     *
     * 栈
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
//        //访问节点的左孩子，并把右孩子压入栈
//        while (curr != null || !stack.isEmpty()) {
//            if (curr == null) {
//                curr = stack.pop().right;
//                if (curr == null) {
//                    continue;
//                }
//            }
//
//            res.add(curr.val);
//            stack.push(curr);
//            curr = curr.left;
//        }

        //另一种写法：先压右孩子入栈，再压左孩子入栈（这样左孩子就可以先出来）
        stack.add(root);
        while (!stack.isEmpty()) {
            curr = stack.pop();
            res.add(curr.val);

            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }

        return res;
    }
}
