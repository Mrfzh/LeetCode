package recursion;

public class Question430 {
    public static void main(String[] args) {

    }

    /**
     * 您将获得一个双向链表，除了下一个和前一个指针之外，它还有一个子指针，可能指向单独的双向链表。
     * 这些子列表可能有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
     *
     * 扁平化列表，使所有结点出现在单级双链表中。您将获得列表第一级的头部。
     *
     * 递归
     *
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        if (head == null) {
            return head;
        }
        helper(head).next = null;

        return head;
    }

    /**
     * 扁平化 head，返回其扁平化后链表的尾节点
     */
    private Node helper(Node head) {
        Node temp = head;
        Node tail = head;
        while (temp != null) {
            tail = temp;
            Node next = temp.next;
            if (temp.child != null) {
                Node childTail = helper(temp.child);
                temp.next = temp.child;
                temp.child.prev = temp;
                // 这部分是关键，最后一个节点有 child 链表时要更新 tail 为 childTail
                if (next != null) {
                    next.prev = childTail;
                } else {
                    tail = childTail;
                }
                childTail.next = next;
                temp.child = null;
            }
            temp = next;
        }

        return tail;
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }
}
