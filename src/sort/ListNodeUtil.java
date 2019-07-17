package sort;

import java.util.ArrayList;
import java.util.List;

public class ListNodeUtil {

    /**
     * 创建链表
     *
     * @param values 链表节点元素集合
     * @return
     */
    public static ListNode buildLinkList(int[] values) {
        if (values.length == 0) {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        for (int i = 1; i < values.length; i++) {
            insertNode(head, new ListNode(values[i]));
        }

        return head;
    }

    /**
     * 在链表中插入新节点
     *
     * @param head 链表头结点
     * @param node 当前插入链表
     * @return
     */
    public static ListNode insertNode(ListNode head, ListNode node) {
        if (head == null) {
            return node;
        }

        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        return head;
    }

    /**
     * 打印链表
     *
     * @param head
     */
    public static void printLinkList(ListNode head) {
        ListNode temp = head;
        List<Integer> list = new ArrayList<>();
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        System.out.println(list);
    }

    /**
     * 反转链表
     *
     * @param head 原链表头节点
     * @return
     */
    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head;
        ListNode reverse = null;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = reverse;
            reverse = temp;
            temp = next;
        }

        return reverse;
    }
}
