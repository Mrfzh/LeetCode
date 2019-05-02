package Link;

import java.util.ArrayList;
import java.util.List;

public class Question25 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode l2 = new ListNode(2);
        head.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        ListNode l4 = new ListNode(4);
        l3.next = l4;
        ListNode l5 = new ListNode(5);
        l4.next = l5;
        l5.next = null;

        print(reverseKGroup(head, 6));
    }

    /**
     * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
     *
     * 利用递归求解
     *
     * @param head 给出链表的头结点
     * @param k 以k个节点为一组
     * @return 翻转后的链表头结点
     */
    private static ListNode reverseKGroup(ListNode head, int k) {
        //计算该链表的结点数
        int n = 0;
        ListNode temp = head;
        while (temp != null) {
            n++;
            temp = temp.next;
        }

        if (n < k) {
            return head;
        }

        List<ListNode> listNodes = new ArrayList<>();   //存前面k个结点
        temp = head;
        for (int i = 0; i < k; i++) {
            listNodes.add(temp);
            temp = temp.next;
        }

        for (int i = 0; i < k; i++) {
            if (i == 0) {
                listNodes.get(i).next = reverseKGroup(listNodes.get(k-1).next, k);
            } else {
                listNodes.get(i).next = listNodes.get(i-1);
            }
        }

        return listNodes.get(k-1);
    }

    private static void print(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }
}
