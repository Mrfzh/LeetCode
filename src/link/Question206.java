package link;

import javafx.util.Pair;

import java.util.Stack;

public class Question206 {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5};
        ListNode head = ListNodeUtil.buildLinkList(values);
        ListNodeUtil.printLinkList(new Question206().reverseList_better(head));
    }

    /**
     * 反转一个单链表。
     *
     * 迭代
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 利用栈存储链表节点
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        // 按照出栈顺序建立链表
        ListNode res = stack.pop();
        ListNode last = res;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            last.next = temp;
            last = temp;
        }
        last.next = null;

        return res;
    }

    /**
     * 递归法
     */
    public ListNode reverseList_recursion(ListNode head) {
        Pair<ListNode, ListNode> res = reverse(head);
        return res.getKey();
    }

    /**
     * Pair 的 key 为反转后的头结点，value 为反转后的尾节点
     */
    private Pair<ListNode, ListNode> reverse(ListNode head) {
        if (head == null) {
            return new Pair<>(null, null);
        } else if (head.next == null) {
            return new Pair<>(head, head);
        }

        // 从第二个节点开始反转
        Pair<ListNode, ListNode> other = reverse(head.next);
        head.next = null;
        other.getValue().next = head;

        return new Pair<>(other.getKey(), head);
    }

    /**
     * 优化后的迭代
     */
    public ListNode reverseList_better(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        return pre;
    }

    /**
     * 优化后的递归
     */
    public ListNode reverseList_recursion_better(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode other = reverseList_recursion_better(head.next);
        head.next.next = head;
        head.next = null;

        return other;
    }
}
