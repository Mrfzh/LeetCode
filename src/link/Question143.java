package link;

import java.util.*;

public class Question143 {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4};
        ListNode head = LinkListUtil.buildLinkList(values);
        LinkListUtil.printLinkList(head);
        new Question143().reorderList_better(head);
        LinkListUtil.printLinkList(head);
    }

    /**
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        //先保存原链表的逆序
        ListNode temp = head;
        Stack<ListNode> stack = new Stack<>();

        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        //将逆序链表节点插入原链表中
        ListNode reverseNode = stack.pop();
        ListNode node = head;
        while (reverseNode != node) {
            ListNode nodeNext = node.next;

            if (reverseNode == nodeNext) {
                nodeNext.next = null;
                return;
            }
            reverseNode.next = node.next;
            node.next = reverseNode;

            reverseNode = stack.pop();
            node = nodeNext;

        }
        node.next = null;
    }

    /**
     * 优化：不需要额外的栈存储逆序后的节点，分三步
     * 1. 利用快慢指针找到链表的中点
     * 2. 逆转后半部分的链表
     * 3. 拼接前半部分和逆转后的后半部分链表
     */
    public void reorderList_better(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        //利用快慢指针找到链表中点
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //逆转后半部分的链表
        ListNode behindHead = slow.next;
        slow.next = null;
        behindHead = reverse(behindHead);
        //拼接前半部分和逆转后的后半部分链表
        ListNode frontHead = head;
        while (frontHead != null && behindHead != null) {
            ListNode frontNext = frontHead.next;
            ListNode behindNext = behindHead.next;
            behindHead.next = frontHead.next;
            frontHead.next = behindHead;
            frontHead = frontNext;
            behindHead = behindNext;
        }
    }

    private ListNode reverse(ListNode head) {
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
