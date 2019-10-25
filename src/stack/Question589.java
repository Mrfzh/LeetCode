package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Question589 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个 N 叉树，返回其节点值的前序遍历。
     *
     * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
     *
     * 栈
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        Stack<Node> stack = new Stack<>();
        if (root != null) {
            stack.add(root);
        }
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            res.add(curr.val);
            // 从后往前遍历，将子节点压入栈中
            List<Node> children =  curr.children;
            for (int i = children.size()-1; i >= 0; i--) {
                stack.add(children.get(i));
            }
        }

        return res;
    }

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
