package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Question133 {
    public static void main(String[] args) {

    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    //利用HashMap存储节点的深拷贝，其中key为原节点，node为原节点的深拷贝
    private HashMap<Node, Node> hashMap = new HashMap<>();

    /**
     * 给定无向连通图中一个节点的引用，返回该图的深拷贝（克隆）。
     * 图中的每个节点都包含它的值 val（Int） 和其邻居的列表（list[Node]）。
     *
     * 递归
     *
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if(!hashMap.containsKey(node)){
            Node copy = new Node(node.val, new ArrayList<>());
            hashMap.put(node, copy);
            for(Node neighbor : node.neighbors){
                copy.neighbors.add(cloneGraph(neighbor));
            }
        }
        return hashMap.get(node);
    }

}
