package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Question429 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
     *
     * 队列（bfs）
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        Node lastNode = root;   // 记录上一层的最后一个节点
        Node tempLastNode = null;      // 暂存当前层的最后一个节点
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> currList = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            currList.add(curr.val);
            // 将当前节点的子节点添加进队列
            List<Node> children = curr.children;
            queue.addAll(children);
            // 更新 tempLastNode
            if (!children.isEmpty()) {
                tempLastNode = children.get(children.size()-1);
            }

            if (curr == lastNode) {
                // 更新结果
                res.add(new ArrayList<>(currList));
                currList.clear();
                // 更新 lastNode
                lastNode = tempLastNode;
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
    };
}
