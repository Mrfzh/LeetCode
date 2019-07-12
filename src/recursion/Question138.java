package recursion;

import java.util.HashMap;

public class Question138 {
    public static void main(String[] args) {

    }

    private HashMap<Node, Node> hashMap = new HashMap<>();
    //key为原链表的节点，value为深拷贝后的节点

    /**
     * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
     * 要求返回这个链表的深拷贝。
     *
     * 递归
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (hashMap.containsKey(head)) {
            return hashMap.get(head);
        }
        Node res = null;
        if (head != null) {
            res = new Node(head.val, null, null);
            hashMap.put(head, res);
            res.next = hashMap.containsKey(head.next)? hashMap.get(head.next) : copyRandomList(head.next);
            res.random = hashMap.containsKey(head.random)? hashMap.get(head.random) : copyRandomList(head.random);
        }
        return res;
    }

    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
}
