package stack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class Question590 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个 N 叉树，返回其节点值的后序遍历。
     *
     * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
     *
     * 栈
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        Stack<Node> stack = new Stack<>();
        if (root != null) {
            stack.add(root);
        }
        List<Integer> res = new ArrayList<>();
        HashSet<Node> hasFound = new HashSet<>();   // 保存已遍历过孩子的节点
        while (!stack.isEmpty()) {
            Node curr = stack.peek();
            // 如果当前节点没有子节点或者已经遍历过子节点，就出栈并加入 res
            List<Node> children = curr.children;
            if (children.isEmpty() || hasFound.contains(curr)) {
                res.add(stack.pop().val);
            } else {
                // 否则从后往前遍历，将其子节点入栈
                for (int i = children.size()-1; i >= 0; i--) {
                    stack.add(children.get(i));
                }
                hasFound.add(curr);
            }
        }

        return res;
    }

    /**
     * 优化：不借助 HashSet 的方法：
     * 反转先序遍历（这里的先序是先遍历父节点，再从右往左遍历子节点）的结果
     */

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
